package org.kbalazsworks.app.Entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericResponse<T>
{
    private T data;
    private int errorCode;
    private String requestId;
}
