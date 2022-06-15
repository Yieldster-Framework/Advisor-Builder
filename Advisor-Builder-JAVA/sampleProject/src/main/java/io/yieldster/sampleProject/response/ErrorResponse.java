package io.yieldster.sampleProject.response;

import lombok.Data;

@Data
public class ErrorResponse extends Response {
    private int status = 0;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, Object body) {
        super(body, message);
    }
}
