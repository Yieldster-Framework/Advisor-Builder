package com.ddc.yieldster.jsonbuilder.service;

import com.ddc.yieldster.jsonbuilder.response.Response;

import java.util.concurrent.CompletableFuture;

public interface JsonService {

    CompletableFuture<Response> toJson();
}
