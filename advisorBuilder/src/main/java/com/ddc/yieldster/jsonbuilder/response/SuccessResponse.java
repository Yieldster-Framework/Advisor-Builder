package com.ddc.yieldster.jsonbuilder.response;

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
