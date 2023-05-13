package org.kbalazsworks.app;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestEntity
{
    private final String token;
}
