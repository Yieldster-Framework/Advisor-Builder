package com.ddc.yieldster.jsonbuilder.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Builder
@Value
@Data
public final class Advisor {
	String type;
	List<Step> steps;
}
