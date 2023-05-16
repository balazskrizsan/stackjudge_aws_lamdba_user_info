package org.kbalazsworks.app.Factories;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.kbalazsworks.app.Exceptions.HttpException;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

public class HttpClientFactory
{
    private KeyStore keyStore(String file, char[] password) throws HttpException
    {
        KeyStore keyStore;

        try
        {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(file);

            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, password);
        }
        catch (Exception e)
        {
            throw new HttpException("Key store loading error: " + e.getMessage(), e);
        }

        return keyStore;
    }

    private SSLContext getSslContext() throws HttpException
    {
        char[] password = "password".toCharArray();

        try
        {
            return SSLContextBuilder
                .create()
                .setSecureRandom(new SecureRandom())
                .loadKeyMaterial(
//                    keyStore("src\\main\\resources\\keystore\\sjdev.p12", password),
//                    keyStore("\\src\\main\\resources\\keystore\\sjdev.p12", password),
                    keyStore("keystore/sjdev.p12", password),
                    password
                )
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();
        }
        catch (Exception e)
        {
            throw new HttpException("SSLContextBuilder failer: " + e.getMessage(), e);
        }
    }

    public CloseableHttpClient build() throws HttpException
    {
        return HttpClients.custom().setSSLContext(getSslContext()).build();
    }
}
