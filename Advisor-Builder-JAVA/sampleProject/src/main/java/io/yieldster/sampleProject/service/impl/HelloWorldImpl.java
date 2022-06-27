package io.yieldster.sampleProject.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.swagger.client.codegen.rest.api.HarvestExecutionServiceApi;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.api.VaultServiceApi;
import com.swagger.client.codegen.rest.model.SDKResponse;
import io.yieldster.sampleProject.exception.JsonBuilderException;
import io.yieldster.sampleProject.exception.JsonBuilderExceptionMessage;
import io.yieldster.sampleProject.model.Advisor;
import io.yieldster.sampleProject.model.ConditionalStep;
import io.yieldster.sampleProject.model.MoveStep;
import io.yieldster.sampleProject.model.Step;
import io.yieldster.sampleProject.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class HelloWorldImpl implements HelloWorldService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Autowired
    private SdkServiceApi sdkServiceApi;
    @Autowired
    private VaultServiceApi vaultServiceApi;

    @Autowired
    private HarvestExecutionServiceApi harvestExecutionServiceApi;

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


    @Override
    public String getHarvestAdvisor(String vaultId) throws JsonBuilderException, JsonProcessingException {
        String zeroToken = "0x0000000000000000000000000000000000000000";
        String cvxToken = "0x4e3fbd56cd56c3e72c1403e103b45db9da5b9d2b";
        String crvToken = "0xd533a949740bb3306d119cc777fa900ba034cd52";
        String _3crvToken = "0x6c3f90f043a72fa612cbac8115ee7e52bde6e490";
        String cvxcrvToken = "0x62b9c7356a2dc64a1969e19c23e4f579f9810aa7";
        List<Step> stepList=new ArrayList<>();
        JsonNode vault = getVault(vaultId);
        String vaultAddress = vault.get("vaultAddress").asText();
        ArrayList<String> stakedPools = getStakedPools(vaultAddress);
        for (String stakedPool : stakedPools) {
            List<String> rewardTokens = getRewardTokens(stakedPool);
            Map<String, BigDecimal> rewardTokenBalanceMap = rewardTokens.stream().collect(Collectors.toMap(token -> token, value -> BigDecimal.ZERO, (elem1, elem12) -> elem1)); // Map of reward token and balance
            for (Map.Entry<String, BigDecimal> entry : rewardTokenBalanceMap.entrySet()) {
                entry.setValue(getTokenBalance(entry.getKey(), vaultAddress)); // Finding balance for each and every rewardToken
            }
            BigDecimal totalUsdPrice = getRewardBalance(vaultAddress,stakedPool).multiply(getUsdPrice(stakedPool, rewardTokens));
            BigDecimal gas = getEstimatedGas(vaultAddress, stakedPool);
            if(gas.compareTo(totalUsdPrice.multiply(BigDecimal.valueOf(5).divide(BigDecimal.valueOf(100)))) <= 0){
                boolean harvestStatus = initiateHarvest(stakedPool, rewardTokens);
                if(!harvestStatus)
                    continue;
                for (Map.Entry<String, BigDecimal> entry : rewardTokenBalanceMap.entrySet()) {
                    BigDecimal newBalance = getTokenBalance(entry.getKey(), vaultAddress);// Finding new balance for each and every rewardToken
                    if(newBalance.compareTo(entry.getValue()) > 0){ // Check for positive difference
                        if(entry.getKey().equalsIgnoreCase(cvxToken)){
                            // cvx
                            stepList.add(MoveStep.builder()
                                    .fromAsset(cvxToken)
                                    .toAsset(zeroToken)
                                    .build());
                        } else {
                            if (entry.getKey().equalsIgnoreCase(crvToken)) {
                                // crv
                                stepList.add(MoveStep.builder()
                                        .fromAsset(crvToken)
                                        .toAsset(zeroToken)
                                        .build());
                            }else {
                                if (entry.getKey().equalsIgnoreCase(_3crvToken)) {
                                    // 3crv
                                    stepList.add(MoveStep.builder()
                                            .fromAsset(_3crvToken)
                                            .toAsset("lptoken")
                                            .build());
                                }else {
                                    if (entry.getKey().equalsIgnoreCase(cvxcrvToken)) {
                                        // cvxcrv
                                        stepList.add(MoveStep.builder()
                                                .fromAsset(cvxcrvToken)
                                                .toAsset(zeroToken)
                                                .build());
                                    }else{
                                        // other tokens
                                        stepList.add(MoveStep.builder()
                                                .fromAsset(entry.getKey())
                                                .toAsset("lptoken")
                                                .build());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Advisor advisor = Advisor.builder()
                .advisorType("Harvest Advisor")
                .steps(stepList).build();
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advisor);
    }

    private boolean initiateHarvest(String stakedPool, List<String> rewardTokens) {
        try {
            SDKResponse response = null;
            if (response == null || response.getData() == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private BigDecimal getEstimatedGas(String vaultAddress, String stakedPool) {
        return null;
    }

    private BigDecimal getUsdPrice(String stakedPool, List<String> rewardTokens) throws JsonBuilderException {
        try {
            SDKResponse response = sdkServiceApi.getTokenPrice1("0xd533a949740bb3306d119cc777fa900ba034cd52", false, null, null); // CRV price
            if (response == null || response.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_PRICE.toString());
            }
            double tokenBalance = OBJECT_MAPPER.convertValue(response.getData(), JsonNode.class).get("TokenPrice").asDouble();
            return BigDecimal.valueOf(tokenBalance);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private ArrayList<String> getStakedPools(String vaultAddress) throws JsonBuilderException {
        try {
            SDKResponse response = vaultServiceApi.getStakedPools(vaultAddress);
            if (response == null || response.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_STAKED_POOLS.toString());
            }
            return OBJECT_MAPPER.convertValue(response.getData(), new TypeReference<ArrayList<String>>() {});
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private ArrayList<String> getRewardTokens(String stakingContractAddress) throws JsonBuilderException {
        try {
            SDKResponse response = harvestExecutionServiceApi.getRewardContract(stakingContractAddress);
            if (response == null || response.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_TOKENS.toString());
            }
            return OBJECT_MAPPER.convertValue(response.getData(), new TypeReference<ArrayList<String>>() {});
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private BigDecimal getRewardBalance(String vaultAddress, String stakingContractAddress) throws JsonBuilderException {
        try {
            SDKResponse response = harvestExecutionServiceApi.getRewardAmount(vaultAddress, stakingContractAddress);
            if (response == null || response.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_BALANCE.toString());
            }
            return OBJECT_MAPPER.convertValue(response.getData(), BigDecimal.class);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private BigDecimal getTokenBalance(String tokenAddress, String vaultAddress) throws JsonBuilderException {
        try {
            SDKResponse sdkResponse = sdkServiceApi.getTokenBalance1(vaultAddress, tokenAddress, null, null);
            if (sdkResponse == null)
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_BALANCE.toString());
            if (sdkResponse.getData() == null)
                throw new JsonBuilderException(sdkResponse.getMessage() != null ? sdkResponse.getMessage() : JsonBuilderExceptionMessage.UNABLE_TO_GET_BALANCE.toString());
            SDKResponse responseData = sdkServiceApi.getTokenDecimal(tokenAddress);
            double decimal = OBJECT_MAPPER.convertValue(responseData.getData(), JsonNode.class).asDouble();
            double tokenBalance = OBJECT_MAPPER.convertValue(sdkResponse.getData(), JsonNode.class).get("Token Balance").asDouble();
            return BigDecimal.valueOf(tokenBalance / Math.pow(10, decimal));
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }

    private JsonNode getVault(String vaultId) throws JsonBuilderException {
        try {
            SDKResponse response = vaultServiceApi.getVaultById(vaultId);
            if (response == null || response.getData() == null) {
                throw new JsonBuilderException(JsonBuilderExceptionMessage.UNABLE_TO_GET_VAULT_DATA.toString());
            }
            return OBJECT_MAPPER.convertValue(response.getData(), JsonNode.class);
        } catch (Exception e) {
            throw new JsonBuilderException(e.getMessage(), e);
        }
    }
}
