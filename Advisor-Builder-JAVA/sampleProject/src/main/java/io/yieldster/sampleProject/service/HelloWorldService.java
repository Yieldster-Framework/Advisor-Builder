package io.yieldster.sampleProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swagger.client.codegen.rest.model.SDKResponse;
import io.yieldster.sampleProject.exception.JsonBuilderException;

public interface HelloWorldService {
    String generateMaximizeAssetReturn() throws JsonProcessingException;

    SDKResponse getTokenPrice(String tokenAddress);

    SDKResponse getVaultAdvisorSetting(String vaultId);

    String getHarvestAdvisor(String vaultId) throws JsonBuilderException, JsonProcessingException;
}
