package org.kbalazsworks.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kbalazsworks.simple_oidc.DiConfigModule;
import com.kbalazsworks.simple_oidc.exceptions.OidcApiException;
import com.kbalazsworks.simple_oidc.services.CommunicationService;
import com.kbalazsworks.simple_oidc.services.HttpClientService;
import com.kbalazsworks.simple_oidc.services.ValidationService;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.kbalazsworks.app.Entities.GenericResponseWithAccountGetResponse;
import org.kbalazsworks.app.Exceptions.HttpException;
import org.kbalazsworks.app.Factories.HttpClientFactory;

import java.io.IOException;


public class App implements RequestHandler<RequestEntity, Boolean>
{
    ObjectMapper objectMapper = new ObjectMapper();
    Injector injector;
    CommunicationService communicationService;
    CloseableHttpClient httpClient;

    public App() throws HttpException
    {
        HttpClientService.host = "https://localhost:5001";
        injector               = Guice.createInjector(new DiConfigModule());
        communicationService   = injector.getInstance(CommunicationService.class);
        this.httpClient        = new HttpClientFactory().build();
    }

    private String getIdsApiAccessToken() throws OidcApiException
    {
        return communicationService.callTokenEndpoint(
            "sj.ids.api",
            "sj.ids.api",
            "IdentityServerApi IdentityServerAccessToken",
            "client_credentials"
        ).getAccessToken();
    }

    private void getIdsUser(String idsApiAccessToken, String idsUserId) throws IOException
    {
        HttpPost request = new HttpPost("https://localhost:5001/api/account/" + idsUserId);
        request.addHeader("Authorization", "Bearer " + idsApiAccessToken);

        String userInfoResponse = EntityUtils.toString(httpClient.execute(request).getEntity());

        GenericResponseWithAccountGetResponse rsp = objectMapper.readValue(
            userInfoResponse,
            GenericResponseWithAccountGetResponse.class
        );
    }

    @SneakyThrows
    @Override
    public Boolean handleRequest(final RequestEntity request, final Context context)
    {
        boolean isValid = injector.getInstance(ValidationService.class).isJwksVerifiedToken(request.getToken());


        getIdsUser(getIdsApiAccessToken(), request.getIdsUserId());

        return isValid;
    }
}
