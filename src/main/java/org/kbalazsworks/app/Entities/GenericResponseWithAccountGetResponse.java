package org.kbalazsworks.app.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponseWithAccountGetResponse
{
    private AccountGetResponse data;
    private boolean success;
    private int errorCode;
    private String requestId;
}

