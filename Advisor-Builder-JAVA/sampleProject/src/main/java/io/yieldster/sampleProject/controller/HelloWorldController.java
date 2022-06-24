package io.yieldster.sampleProject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swagger.client.codegen.rest.model.SDKResponse;
import io.yieldster.sampleProject.exception.JsonBuilderException;
import io.yieldster.sampleProject.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class HelloWorldController {
    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("/getJson")
    public String getJson() throws JsonProcessingException {
        return helloWorldService.generateMaximizeAssetReturn();
    }

    @GetMapping("/harvest-advisor")
    public String harvestAdvisor(@RequestParam String vaultId) throws JsonProcessingException, JsonBuilderException {
        return helloWorldService.getHarvestAdvisor(vaultId);
    }

    @GetMapping("/tokenPrice")
    public SDKResponse getTokenPrice() {
        return helloWorldService.getTokenPrice("0x6B175474E89094C44Da98b954EedeAC495271d0F");
    }

    @GetMapping("/getAssetByVaultId/{id}")
    public SDKResponse getAssetByVaultId(@PathVariable String id) {
        return helloWorldService.getVaultAdvisorSetting(id);
    }
}
