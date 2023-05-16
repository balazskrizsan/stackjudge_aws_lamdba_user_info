package org.kbalazsworks.app.Entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenClientCredentials
{
    @JsonAlias({"access_token" })
    private String accessToken;
    @JsonAlias({"expires_in" })
    private String expiresIn;
    @JsonAlias({"token_type" })
    private String tokenType;
    private String scope;
}
