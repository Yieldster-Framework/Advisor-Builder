package com.ddc.yieldster.jsonbuilder.service.impl;

import com.ddc.yieldster.jsonbuilder.model.Advisor;
import com.ddc.yieldster.jsonbuilder.model.ConditionalStep;
import com.ddc.yieldster.jsonbuilder.model.MoveStep;
import com.ddc.yieldster.jsonbuilder.service.HelloWorldService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.swagger.client.codegen.rest.api.SdkServiceApi;
import com.swagger.client.codegen.rest.model.SDKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class HelloWorldImpl implements HelloWorldService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Autowired
    private SdkServiceApi sdkServiceApi;

    //Function to get NAV of the provided vault
    //@Param: vaultAddress:- Address of the required vault
    //@Param: date( DD-MM-YYYY HH:MM:SS format) / timestamp. if null uses current blockNumber by default
    //@Param: boolean:- true if provided above param is date; false if the provided param is timestamp
    public SDKResponse getVaultNAV(String vaultAddress) {
        return sdkServiceApi.getVaultNAV(vaultAddress, null, null);
    }

    //Function to get list of invested asset of the provided vault
    //@Param: vaultAddress:- Address of the required vault
    public SDKResponse getVaultAssets(String vaultAddress) {
        return sdkServiceApi.getVaultAssets(vaultAddress);
    }

    //Function to get price of token( stable coin or vault token)
    //@Param: tokenAddress:- Address of the required token
    //@Param: boolean:- true: if the provided tokenAddress is a vault token, false:- if the tokenAddress is stable coin
    //@Param: date( DD-MM-YYYY HH:MM:SS format) / timestamp. if null uses current blockNumber by default
    //@Param: boolean:- true if provided above param is date; false if the provided param is timestamp
    public SDKResponse getTokenPrice(String tokenAddress) {
        return sdkServiceApi.getTokenPrice(tokenAddress, false, null, null);
    }

    /*
    This advisor checks if the increase in price of yvCurve_FRAX over the period of 10 days is above a certain
    threshold (in this case 0.5) the available investable asset (AD) present in vault is swapped to get yvCurve_FRAX
     * */
    public String generateMaximizeAssetReturn() throws JsonProcessingException {

        String yvCurve_FRAX = "0xB4AdA607B9d6b2c9Ee07A275e9616B84AC560139";
        SDKResponse currentTokenData = sdkServiceApi.getTokenPrice(yvCurve_FRAX, false, null, null);
        SDKResponse previousTokenData = sdkServiceApi.getTokenPrice(yvCurve_FRAX, false, "1650737071", false);
        if (currentTokenData.getStatusCode() == 200 && previousTokenData.getStatusCode() == 200) {
            Map<String, Object> currentTokenDataMap = (Map<String, Object>) currentTokenData.getData();
            Map<String, Object> previousTokenDataMap = (Map<String, Object>) previousTokenData.getData();

            Long currentTokenPrice = (Long) currentTokenDataMap.get("Token Price");
            Long previousTokenPrice = (Long) previousTokenDataMap.get("Token Price");
            // The difference in price percentage is calculated.
            double priceDifferencePercentage = ((currentTokenPrice - previousTokenPrice) / previousTokenPrice) * 100;
            if (priceDifferencePercentage > 0.5) {
                Advisor advisor = Advisor.builder()
                        .type("Sample_Advisor")
                        .steps(ImmutableList.of(
                                MoveStep.builder()
                                        .fromAsset("AD")
                                        .toAsset(yvCurve_FRAX)
                                        .build())).build();
                return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advisor);
            }
        }
        return null;
    }

    //Sample Advisor: Maximize APR
    //Here the variable used like AD, TMin ... are predefined ones that are recognizable by the Yieldster Application and the corresponding value is
    // replaced during it's execution
    //In this type of Advisor most of the computation is done in the backend
    private Advisor createAdvisorMaximizeAPR() {
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
}
