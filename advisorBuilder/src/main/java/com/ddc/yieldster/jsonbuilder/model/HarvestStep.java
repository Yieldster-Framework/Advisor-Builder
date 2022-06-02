package com.ddc.yieldster.jsonbuilder.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Builder
@Value
@Data
public class HarvestStep extends Step {
    String type = "HarvestStep";
    String stakingContract;
    List<String> returnTokens;
}
