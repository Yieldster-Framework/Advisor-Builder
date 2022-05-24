package io.yieldster.sampleProject.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Value
@Data
public class MoveStep extends Step {
    String type="MoveStep";
    String fromAsset;
    String toAsset;
}
