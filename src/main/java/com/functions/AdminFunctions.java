package com.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

public class AdminFunctions {

    @FunctionName("HttpTriggerJavaVersion2")
    public static HttpResponseMessage HttpTriggerJavaVersion(
        @HttpTrigger(
            name = "req",
            methods = {HttpMethod.GET, HttpMethod.POST},
            authLevel = AuthorizationLevel.ANONYMOUS)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        context.getLogger().info("Java HTTP trigger processed a request.");
        final String javaVersion = getJavaVersion();
        context.getLogger().info("Function - HttpTriggerJavaVersion" + javaVersion);
        return request.createResponseBuilder(HttpStatus.OK).body(javaVersion).build();
    }

    public static String getJavaVersion() {
        return String.join(" - ", System.getProperty("java.home"), System.getProperty("java.version"));
    }

}
