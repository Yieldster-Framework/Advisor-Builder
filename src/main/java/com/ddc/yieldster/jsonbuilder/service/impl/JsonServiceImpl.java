package com.ddc.yieldster.jsonbuilder.service.impl;

import com.ddc.yieldster.jsonbuilder.model.Advisor;
import com.ddc.yieldster.jsonbuilder.model.ConditionalStep;
import com.ddc.yieldster.jsonbuilder.model.MoveStep;
import com.ddc.yieldster.jsonbuilder.response.ErrorResponse;
import com.ddc.yieldster.jsonbuilder.response.Response;
import com.ddc.yieldster.jsonbuilder.response.SuccessResponse;
import com.ddc.yieldster.jsonbuilder.service.JsonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class
JsonServiceImpl implements JsonService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public CompletableFuture<Response> toJson() {
        try {
            System.out.println("3333");

            String result =  OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(createAdvisor());
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



    public String portFolioAllocator(String vaultId){

        Map<String,Object> vaultSettings = getVaultSetting(vaultId);
        Map<String,Integer> assetAllocation = (Map<String, Integer>) vaultSettings.get("assetAllocation");

        if (vaultSettings.get("forcedRebalance").equals(true) || (getNavOfAssets(vaultId,assetAllocation)>=(Double)vaultSettings.get("minTransactionSize"))) {
            //TODO: ADD LOGIC IMPLEMENTATION HERE

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
                    2.2.3) For each of L[j]
                        A) If L[i].totalPrice >=Tmin && amountToInvest >= Tmin
                            A.1) if L[j].totalPrice >=amountToInvest
                                A.1.1) Move L[j].totalPrice / amountToInvest amount of assets to AP[i]
                                A.1.2) Break
                            A.2) else
                                A.2.1) Move L[j].balance amounts of assets to AP[i]
                        B) else, Increment i
                    2.2.4) Increment i
                2.3) else, increment i

             ---------------------------------------------------------------------------------------
             */




        return null;
    }

    private Map<String,Object> getVaultSetting(String vaultId){
        return null;
    }


    private Double getNavOfAssets(String vaultId,Map<String,Integer> assetAllocation){
        return null;
    }

}
