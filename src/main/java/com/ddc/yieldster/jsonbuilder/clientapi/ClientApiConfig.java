package com.ddc.yieldster.jsonbuilder.clientapi;


import com.swagger.client.codegen.rest.api.PathExecutionServiceApi;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientApiConfig {
    @Value("${client.host.uri}")
    private String hostBasePath;

    @Bean
    public ApiClient apiClient() {

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(hostBasePath);
        apiClient.setAccessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0X3VzZXJAeWllbGRzdGVyLmlvIiwiaWF0IjoxNjUxNjQzNjk0LCJleHAiOjE2NTE2NTIzMzR9.BWe6Elt3UVLqEjgEIdVTCw3PLVM0VwiQRXs0e2zYRphIUu24cjKzHQa1CNUiZiy839lDqbjjbadDgwqF_ndPMQ");
        return apiClient;
    }

    @Bean
    public PathExecutionServiceApi pathExecutionServiceApi() {
        return new PathExecutionServiceApi(apiClient());
    }

    @Bean
    public SdkServiceApi sdkServiceApi() {
        return new SdkServiceApi(apiClient());
    }
}
