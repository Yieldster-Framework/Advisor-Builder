package com.ddc.yieldster.jsonbuilder.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Builder
@Value
@Data
public class ConditionalStep extends Step {
    String type = "ConditionalStep";
    String conditionalStatement;
    List<Step> trueExecuteStep;
    List<Step> falseExecuteStep;
}
