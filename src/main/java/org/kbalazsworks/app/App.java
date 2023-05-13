package org.kbalazsworks.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.kbalazsworks.simple_oidc.DiConfigModule;
import com.kbalazsworks.simple_oidc.services.HttpClientService;
import com.kbalazsworks.simple_oidc.services.ValidationService;


public class App implements RequestHandler<RequestEntity, Boolean>
{
    Injector injector;

    public App()
    {
        HttpClientService.host = "https://localhost:5001";

        injector = Guice.createInjector(new DiConfigModule());
    }

    @Override
    public Boolean handleRequest(final RequestEntity request, final Context context)
    {
        boolean isValid = injector.getInstance(ValidationService.class).isJwksVerifiedToken(request.getToken());

        return isValid;
    }
}
