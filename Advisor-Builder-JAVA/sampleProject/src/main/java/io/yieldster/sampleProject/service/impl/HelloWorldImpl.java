package io.yieldster.sampleProject.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.api.VaultServiceApi;
import com.swagger.client.codegen.rest.model.SDKResponse;
import io.yieldster.sampleProject.exception.JsonBuilderException;
import io.yieldster.sampleProject.exception.JsonBuilderExceptionMessage;
import io.yieldster.sampleProject.model.Advisor;
import io.yieldster.sampleProject.model.ConditionalStep;
import io.yieldster.sampleProject.model.MoveStep;
import io.yieldster.sampleProject.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;


@Service
public class HelloWorldImpl implements HelloWorldService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Autowired
    private SdkServiceApi sdkServiceApi;
    @Autowired
    private VaultServiceApi vaultServiceApi;

    //Function to get NAV of the provided vault
    //@Param: vaultAddress:- Address of the required vault
    //@Param: date( DD-MM-YYYY HH:MM:SS format) / timestamp. if null uses current blockNumber by default
    //@Param: boolean:- true if provided above param is date; false if the provided param is timestamp
    public SDKResponse getVaultNAV(String vaultAddress) {
        return sdkServiceApi.getVaultNAV(vaultAddress, null, null);
    }

    //Function to get getVaultAdvisorSetting of a vault
    //@Param: vaultId:- Id of the required vault
    public SDKResponse getVaultAdvisorSetting(String vaultId) {
        return vaultServiceApi.getAdvisorSettingByVaultId(vaultId);
    }

    //Function to get price of token( stable coin or vault token)
    //@Param: tokenAddress:- Address of the required token
    //@Param: boolean:- true: if the provided tokenAddress is a vault token, false:- if the tokenAddress is stable coin
    //@Param: date( DD-MM-YYYY HH:MM:SS format) / timestamp. if null uses current blockNumber by default
    //@Param: boolean:- true if provided above param is date; false if the provided param is timestamp
    public SDKResponse getTokenPrice(String tokenAddress) {
        return sdkServiceApi.getTokenPrice1(tokenAddress, false, null, null);
    }

    /*
    This advisor checks if the increase in price of yvCurve_FRAX over the period of 10 days is above a certain
    threshold (in this case 0.5) the available investable asset (Ad) present in vault is swapped to get yvCurve_FRAX
     * */
    public String generateMaximizeAssetReturn() throws JsonProcessingException {

        String yvCurve_FRAX = "0xB4AdA607B9d6b2c9Ee07A275e9616B84AC560139";
        SDKResponse currentTokenData = sdkServiceApi.getTokenPrice1(yvCurve_FRAX, false, null, null);
        SDKResponse previousTokenData = sdkServiceApi.getTokenPrice1(yvCurve_FRAX, false, "1655292773", false);
        if (currentTokenData.getStatusCode() == 200 && previousTokenData.getStatusCode() == 200) {
            Map<String, Object> currentTokenDataMap = (Map<String, Object>) currentTokenData.getData();
            Map<String, Object> previousTokenDataMap = (Map<String, Object>) previousTokenData.getData();

            Long currentTokenPrice = (Long) currentTokenDataMap.get("TokenPrice");
            Long previousTokenPrice = (Long) previousTokenDataMap.get("Token Price");
            // The difference in price percentage is calculated.
            double priceDifferencePercentage = ((currentTokenPrice - previousTokenPrice) / previousTokenPrice) * 100;
            if (priceDifferencePercentage < 0.5) {
                Advisor advisor = Advisor.builder()
                        .advisorType("Sample_Advisor")
                        .steps(ImmutableList.of(
                                MoveStep.builder()
                                        .fromAsset("Ad")
                                        .toAsset(yvCurve_FRAX)
                                        .build())).build();
                return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advisor);
            } else return null;
        } else return null;
    }

    //Sample Advisor: Maximize APR
    //Here the variable used like Ad, TMin ... are predefined ones that are recognizable by the Yieldster Application and the corresponding value is
    // replaced during it's execution
    //In this type of Advisor most of the computation is done in the backend
    private Advisor createAdvisorMaximizeAPR() {
        return Advisor.builder()
                .advisorType("Maximize_APR")
                .steps(ImmutableList.of(
                        ConditionalStep.builder()
                                .conditionalStatement("Ad >= TMin")
                                .trueExecuteStep(ImmutableList.of(
                                        ConditionalStep.builder()
                                                .conditionalStatement("APRMax <= APRInv")
                                                .trueExecuteStep(ImmutableList.of(
                                                        MoveStep.builder()
                                                                .fromAsset("Ad")
                                                                .toAsset("APInv")
                                                                .build()
                                                ))
                                                .falseExecuteStep(ImmutableList.of(
                                                        ConditionalStep.builder()
                                                                .conditionalStatement("IDMax * BTotal > P - Gas")
                                                                .trueExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("(APInv + Ad)")
                                                                                .toAsset("APMax")
                                                                                .build()
                                                                ))
                                                                .falseExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("Ad")
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
                                                                .conditionalStatement("IDMax * BTotal > P - Gas")
                                                                .trueExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("(APInv + Ad)")
                                                                                .toAsset("APMax")
                                                                                .build()
                                                                ))
                                                                .falseExecuteStep(ImmutableList.of(
                                                                        MoveStep.builder()
                                                                                .fromAsset("Ad")
                                                                                .toAsset("APinv")
                                                                                .build()
                                                                ))
                                                                .build()
                                                ))
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }


    public String getConvexAdvisor(String vaultId) throws JsonBuilderException {
        JsonNode vault = getVault(vaultId);
        String vaultAddress = vault.get("vaultAddress").asText();
        ArrayList<String> stakedPools = getStakedPools(vaultAddress);
        BigDecimal totalUsdPrice = BigDecimal.ZERO;
        for (String stakedPool : stakedPools) {
            ArrayList<String> rewardTokens = getRewardTokens(stakedPool);
            totalUsdPrice = totalUsdPrice.add(getRewardBalance(stakedPool).multiply(getUsdPrice()));
        }
        BigDecimal gas = getEstimatedGas();
        if(gas.compareTo(totalUsdPrice.multiply(BigDecimal.valueOf(5).divide(BigDecimal.valueOf(100)))) <= 0){

        }
        return null;
    }

    private BigDecimal getEstimatedGas() {
        return null;
    }

    private BigDecimal getUsdPrice() {
        return null;
    }

    private ArrayList<String> getStakedPools(String vaultAddress) throws JsonBuilderException {
        try {
            SDKResponse vaultResponseData = vaultServiceApi.getStakedPools(vaultAddress);
            if (vaultResponseData == null || vaultResponseData.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_STAKED_POOLS.toString());
            }
            return OBJECT_MAPPER.convertValue(vaultResponseData.getData(), new TypeReference<ArrayList<String>>() {});
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private ArrayList<String> getRewardTokens(String stakingContractAddress) throws JsonBuilderException {
        try {
            SDKResponse vaultResponseData = vaultServiceApi.getStakedPools(stakingContractAddress);
            if (vaultResponseData == null || vaultResponseData.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_STAKED_POOLS.toString());
            }
            return OBJECT_MAPPER.convertValue(vaultResponseData.getData(), new TypeReference<ArrayList<String>>() {});
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private BigDecimal getRewardBalance(String stakingContractAddress) throws JsonBuilderException {
        try {
            SDKResponse vaultResponseData = vaultServiceApi.getStakedPools(stakingContractAddress);
            if (vaultResponseData == null || vaultResponseData.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_STAKED_POOLS.toString());
            }
            return OBJECT_MAPPER.convertValue(vaultResponseData.getData(), BigDecimal.class);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private JsonNode getVault(String vaultId) throws JsonBuilderException {
        try {
            SDKResponse vaultResponseData = vaultServiceApi.getVaultById(vaultId);
            if (vaultResponseData == null || vaultResponseData.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_VAULT_DATA.toString());
            }
            return OBJECT_MAPPER.convertValue(vaultResponseData.getData(), JsonNode.class);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

}
