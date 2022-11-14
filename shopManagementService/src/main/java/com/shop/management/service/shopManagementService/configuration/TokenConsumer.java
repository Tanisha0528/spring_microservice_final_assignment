package com.shop.management.service.shopManagementService.configuration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("authentication-service/authentication")
public interface TokenConsumer {



    @GetMapping("/get-token/{token}")
    public String getIdFromToken(@PathVariable("token") String token);
}
