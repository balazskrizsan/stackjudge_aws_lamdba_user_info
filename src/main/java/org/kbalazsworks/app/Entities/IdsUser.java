package org.kbalazsworks.app.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdsUser
{
    private String  id;
    private String  userName;
    private String  normalizedUserName;
    private String  email;
    private boolean emailConfirmed;
    private String  profileUrl;
}
