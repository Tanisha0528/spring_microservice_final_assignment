package com.consumer.service.consumerService.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("admin-service/user")
public interface UserRestConsumer {



    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map);





}
