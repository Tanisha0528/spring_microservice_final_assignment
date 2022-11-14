package com.consumer.service.consumerService.controller;


import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("authentication-service/authentication")
public interface AuthenticationConsumer {

    @GetMapping("/create-token/{id}")
    public String createToken(@PathVariable("id") ObjectId id);


}