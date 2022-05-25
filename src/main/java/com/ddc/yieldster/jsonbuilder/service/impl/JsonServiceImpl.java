package com.ddc.yieldster.jsonbuilder.service.impl;

import com.ddc.yieldster.jsonbuilder.exception.JsonBuilderException;
import com.ddc.yieldster.jsonbuilder.exception.JsonBuilderExceptionMessage;
import com.ddc.yieldster.jsonbuilder.model.Advisor;
import com.ddc.yieldster.jsonbuilder.model.ConditionalStep;
import com.ddc.yieldster.jsonbuilder.model.MoveStep;
import com.ddc.yieldster.jsonbuilder.model.Step;
import com.ddc.yieldster.jsonbuilder.response.ErrorResponse;
import com.ddc.yieldster.jsonbuilder.response.Response;
import com.ddc.yieldster.jsonbuilder.response.SuccessResponse;
import com.ddc.yieldster.jsonbuilder.service.JsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.api.VaultServiceApi;
import com.swagger.client.codegen.rest.model.SDKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class
JsonServiceImpl implements JsonService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private SdkServiceApi sdkServiceApi;

    @Autowired
    private VaultServiceApi vaultServiceApi;

    @Override
    public CompletableFuture<Response> toJson() {
        try {
            System.out.println("3333");

            String result = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(createAdvisor());
            System.out.println(result);
            return CompletableFuture.completedFuture(new SuccessResponse(result, "Successfully converted to json"));
        } catch (JsonProcessingException e) {
            return CompletableFuture.completedFuture(new ErrorResponse("JsonProcessingException", null));
        }
    }

    private Advisor createAdvisor() {
        return Advisor.builder()
                .type("Maximize_APR")
                .steps(ImmutableList.of(
                        ConditionalStep.builder()
                                .conditionalStatement("AD >= TMin")
                                .trueExecuteStep(ImmutableList.of(
                                        ConditionalStep.builder()
                                                .conditionalStatement("APRMax <= APRInv")
                                                .trueExecuteStep(ImmutableList.of(
                                                        MoveStep.builder()
                                                                .fromAsset("AD")
                                                                .toAsset("APInv")
                                                                .build()
                                                ))
                                                .falseExecuteStep(ImmutableList.of(
                                                        ConditionalStep.builder()
                                                                .conditionalStatement("IDMax * BTotal > Pin + Pout - Gas")
                                                                .trueExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("(APInv + AD)")
                                                                                .toAsset("APMax")
                                                                                .build()
                                                                ))
                                                                .falseExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("AD")
                                                                                .toAsset("APInv")
                                                                                .build()
                                                                ))
                                                                .build()
                                                ))
                                                .build()
                                ))
                                .falseExecuteStep(ImmutableList.of(
                                        ConditionalStep.builder()
                                                .conditionalStatement("APRMax > APRInv")
                                                .trueExecuteStep(ImmutableList.of(
                                                        ConditionalStep.builder()
                                                                .conditionalStatement("IDMax * BTotal > Pin + Pout - Gas")
                                                                .trueExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("(APInv + AD)")
                                                                                .toAsset("APMax")
                                                                                .build()
                                                                ))
                                                                .falseExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("AD")
                                                                                .toAsset("APinv")
                                                                                .build()
                                                                ))
                                                                .build()
                                                ))
                                                .build()
                                ))
                                .build(),
                        ConditionalStep.builder()
                                .conditionalStatement("active AP (APINV) is disabled")
                                .trueExecuteStep(ImmutableList.of(
                                        MoveStep.builder()
                                                .fromAsset("(APInv + AD)")
                                                .toAsset("APMax")
                                                .build()
                                ))
                                .falseExecuteStep(null)
                                .build()
                ))
                .build();
    }


    public Advisor portFolioAllocator(String vaultId) throws JsonBuilderException {
        JsonNode vault = getVault(vaultId);
        String vaultAddress = vault.get("vaultAddress").asText();
        List<Step> steps = new ArrayList<>();
        JsonNode vaultSettings = vault.get("vaultAdvisorSetting");
        Double tMin = vaultSettings.get("tMin").asDouble();
        Map<String, Double> assetAllocation = OBJECT_MAPPER.convertValue(vaultSettings.get("assetAllocation"), new TypeReference<Map<String, Double>>() {
        });
        List<String> investableAssets = OBJECT_MAPPER.convertValue(vault.get("supportedAssets"), new TypeReference<List<String>>() {
        });
        List<String> apList = OBJECT_MAPPER.convertValue(vault.get("supportedProtocols"), new TypeReference<List<String>>() {
        });
        if (vaultSettings.get("forcedRebalance").asBoolean() || (getNavOfAssets(vaultAddress) >= vaultSettings.get("minTransactionSize").asDouble())) {
            for (String api : apList) {
                Double vNav = getNavOfAssets(vaultAddress);
                double currentAllocation = priceOfProtocolToken(api) * balanceOf(api, vaultAddress) / vNav;
                if (currentAllocation <= assetAllocation.get(api)) {
                    Double delta = assetAllocation.get(api) - currentAllocation;
                    Double amountToInvest = delta * vNav;
                    if (amountToInvest >= tMin) {
                        for (String investableAsset : investableAssets) {
                            Double totalPrice = priceOfProtocolToken(investableAsset);
                            steps.add(MoveStep.builder().fromAsset(investableAsset).toAsset(api).build());
                            if (totalPrice >= amountToInvest) {
                                steps.add(MoveStep.builder().fromAsset(investableAsset).toAsset(api).amount(totalPrice / amountToInvest).build());
                            } else {
                                steps.add(MoveStep.builder().fromAsset(investableAsset).toAsset(api).amount(balanceOf(investableAsset, vaultAddress)).build());
                                amountToInvest -= totalPrice;
                            }
                        }
                    }
                }
            }

            /**
             Data needed:
             1) mapping of investable vault assets
             2)

             ---------------------------------------LOGIC-------------------------------------------
             Variables
             1) Tmin    := Minimum Transaction Size
             2) AP1-APn := List of protocols to invest in (Sorted in Ascending order of allocation). Each AP[i], will have
             {
             protocolID,
             protocolAllocation
             }
             3) VNAV  := VaultNAV
             4) L1-Ln := List of investable assets (Sorted in Descending order of totalPrice). Each L[i], will have
             {
             balance,
             totalPrice
             }

             Pseudocode
             1) Get list of investable assets, L
             2) While each of AP[i]
             2.1) currentAllocation := priceOfProtocolToken(AP[i].protocolID) * ERC20<protocolID>.balanceOf(vaultId)  / VNAV * 100
             2.2) if(currentAllocation<=AP[i].protocolAllocation)
             2.2.1) delta := AP[i].protocolAllocation - currentAllocation
             2.2.2) amountToInvest := delta * VNAV
             2.2.3) if(amountToInvest >= Tmin)
             A) For each of L[j]
             A.1) if L[j].totalPrice >=amountToInvest
             A.1.1) Move L[j].totalPrice / amountToInvest amount of assets to AP[i]
             A.1.2) Break for loop
             A.2) else
             A.2.1) Move L[j].balance amounts of assets to AP[i]
             A.2.2) amountToInvest := amountToInvest - L[j].totalPrice
             2.3) Increment i
             ---------------------------------------------------------------------------------------
             */

        }
        return Advisor.builder().type("portFolioAllocator").steps(steps).build();
    }

    private Double getNavOfAssets(String vaultAddress) throws JsonBuilderException {
        try {
            SDKResponse sdkResponse = sdkServiceApi.getVaultNAV(vaultAddress, null, null);
            if (sdkResponse == null)
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_NAV.toString());
            if (sdkResponse.getData() == null)
                throw new JsonBuilderException(sdkResponse.getMessage() != null ? sdkResponse.getMessage() : JsonBuilderExceptionMessage.UNABLE_TO_GET_NAV.toString());
            return OBJECT_MAPPER.convertValue(sdkResponse.getData(), JsonNode.class).get("VaultNAV").asDouble();
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private Double priceOfProtocolToken(String protocolId) throws JsonBuilderException {
        try {
            SDKResponse sdkResponse = sdkServiceApi.getTokenPrice(protocolId, false, null, null);
            if (sdkResponse == null)
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_TOKEN_PRICE.toString());
            if (sdkResponse.getData() == null)
                throw new JsonBuilderException(sdkResponse.getMessage() != null ? sdkResponse.getMessage() : JsonBuilderExceptionMessage.UNABLE_TO_GET_TOKEN_PRICE.toString());
            return OBJECT_MAPPER.convertValue(sdkResponse.getData(), JsonNode.class).get("Token Price").asDouble();
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private Double balanceOf(String tokenAddress, String vaultAddress) throws JsonBuilderException {
        try {
            SDKResponse sdkResponse = sdkServiceApi.getTokenBalance(tokenAddress, vaultAddress, null, null);
            if (sdkResponse == null)
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_BALANCE.toString());
            if (sdkResponse.getData() == null)
                throw new JsonBuilderException(sdkResponse.getMessage() != null ? sdkResponse.getMessage() : JsonBuilderExceptionMessage.UNABLE_TO_GET_BALANCE.toString());
            return OBJECT_MAPPER.convertValue(sdkResponse.getData(), JsonNode.class).get("Token Balance").asDouble();
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private Double getTotalPrice(String investableAsset) {
        return null;
    }

    private JsonNode getVault(String vaultId) throws JsonBuilderException {
        try {
            com.swagger.client.codegen.rest.model.Response vaultResponseData = vaultServiceApi.getVaultById(vaultId);
            if (vaultResponseData == null || vaultResponseData.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_VAULT_DATA.toString());
            }
            return OBJECT_MAPPER.convertValue(vaultResponseData.getData(), JsonNode.class);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

}
