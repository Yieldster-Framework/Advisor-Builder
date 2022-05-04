package com.ddc.yieldster.jsonbuilder.controller;

import com.ddc.yieldster.jsonbuilder.service.HelloWorldService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.swagger.client.codegen.rest.model.SDKResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("/getJson")
    public String getJson() throws JsonProcessingException {
        return helloWorldService.generateMaximizeAssetReturn();
    }

    @GetMapping("/tokenPrice")
    public SDKResponse getTokenPrice(){
        return helloWorldService.getTokenPrice("0x6B175474E89094C44Da98b954EedeAC495271d0F");
    }
}
