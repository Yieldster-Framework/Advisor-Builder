package io.yieldster.sampleProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swagger.client.codegen.rest.model.SDKResponse;

public interface HelloWorldService {
    String generateMaximizeAssetReturn() throws JsonProcessingException;

    SDKResponse getTokenPrice(String tokenAddress);

    SDKResponse getVaultAdvisorSetting(String vaultId);
}
