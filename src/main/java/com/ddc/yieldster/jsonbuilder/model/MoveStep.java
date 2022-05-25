package com.ddc.yieldster.jsonbuilder.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
@Data
public class MoveStep extends Step {
	 private String fromAsset;
	 private String toAsset;
	 private Double amount;
}
