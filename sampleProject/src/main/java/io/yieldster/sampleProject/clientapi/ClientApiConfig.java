package io.yieldster.sampleProject.clientapi;


import com.swagger.client.codegen.rest.api.PathExecutionServiceApi;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.api.VaultServiceApi;
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

    @Bean
    public VaultServiceApi vaultServiceApi() {
        return new VaultServiceApi(apiClient());
    }
}
