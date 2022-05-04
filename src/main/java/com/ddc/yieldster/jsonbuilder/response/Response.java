package com.ddc.yieldster.jsonbuilder.response;

import lombok.Data;

@Data
public class Response {

    protected Object body;
    protected String message;

    public Response() {
    }

    public Response(Object body, String message) {
        this.body = body;
        this.message = message;
    }
}
