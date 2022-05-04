package com.ddc.yieldster.jsonbuilder.controller;

import com.ddc.yieldster.jsonbuilder.exception.ExceptionHelper;
import com.ddc.yieldster.jsonbuilder.response.Response;
import com.ddc.yieldster.jsonbuilder.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/toJson")
public class JsonController {
    @Autowired
    private JsonService jsonService;

    @GetMapping()
    public CompletableFuture<ResponseEntity<Response>> toJson() {
        System.out.println("hai");
        return jsonService.toJson().thenApply(ResponseEntity::ok).exceptionally(ExceptionHelper.handleException);
    }
}
