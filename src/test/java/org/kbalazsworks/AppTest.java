package org.kbalazsworks;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.kbalazsworks.app.App;
import org.kbalazsworks.app.RequestEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest
{
    @Test
    @SneakyThrows
    public void handleRequest_shouldReturnConstantValue()
    {
        // Arrange
        String testedToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjM2NjNERTczMkE5MTA3NDZCODVGODNERERCNDIwMDlDIiwidHlwIjoiYXQrand0In0.eyJpc3MiOiJodHRwczovL2xvY2FsaG9zdDo1MDAxIiwibmJmIjoxNjgzNzQ0ODA0LCJpYXQiOjE2ODM3NDQ4MDQsImV4cCI6MTY4Mzc1OTIwNCwiYXVkIjpbInNqLnJlc291cmNlLmZyb250ZW5kIiwic2oucmVzb3VyY2UuaWRzIiwiaHR0cHM6Ly9sb2NhbGhvc3Q6NTAwMS9yZXNvdXJjZXMiXSwic2NvcGUiOlsib3BlbmlkIiwicHJvZmlsZSIsInNqIiwic2ouZnJvbnRlbmQiXSwiYW1yIjpbImV4dGVybmFsIl0sImNsaWVudF9pZCI6InNqLmZyb250ZW5kIiwic3ViIjoiYjQ0MDllZDItMzYwNy00ZWY0LTllNGQtZDNhMmMxNzcyY2MwIiwiYXV0aF90aW1lIjoxNjgzMTQ4OTQyLCJpZHAiOiJGYWNlYm9vayIsInNpZCI6Ijg1Mjc4M0QxQUIwRjg5QzhDOUJDRkFGMzdDRjI1MjhEIiwianRpIjoiNjg1NDE4MDFBMkVDRkIyMkU5NjU0MzA0MjA2OTNERUYifQ.chlAlLk1YnS8BomTHtXO2jQFha2houlIbHlz8QYLjQmsm-jTIyL4KFHGwP27eCXMiBJVR3nVpPLzkOJrReBQ8To-lbUMvgwQl1JbUxaePVRi0ahuRebZYaHt1Cs2beCeFkApG0qLAQApuvAvlfmNvOX6xEpZTd0Y6l-i5BMHGTYOgNnm1e0PjiRAvv6byHlJgr4QCjZxcfBgYbjxNaY4_3W_znRYV3q6eHk3gUs76fu7kgt9lteHo9gO6zDa8bCEzHpJCxziFVP0uIsJweQm2CJQlxap0pugVTyJQRWMl-HG71Vt8hndtI2TNwEvxZsX9DxM0MwEpH8DSCedN2XIoA";

        // Act
        boolean result = new App().handleRequest(new RequestEntity("1", testedToken), null);

        // Assert
        assertTrue(result);
    }
}
