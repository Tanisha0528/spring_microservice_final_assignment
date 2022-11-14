package com.authentication.service.authenticationService.controller;

import com.authentication.service.authenticationService.service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/create-token/{id}")
    public String createToken(@PathVariable("id") ObjectId id){
        return tokenService.createToken(id);
    }
    @GetMapping("/get-token/{token}")
    public String getIdFromToken(@PathVariable("token") String token){
        return tokenService.getIdFromToken(token);
    }

}
