package io.yieldster.sampleProject.response;

import lombok.Data;

@Data
public class SuccessResponse extends Response {
    private int status = 1;

    public SuccessResponse() {
    }

    public SuccessResponse(Object body, String message) {
        super(body, message);
    }
}
