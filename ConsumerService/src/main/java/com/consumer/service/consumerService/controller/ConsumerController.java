package com.consumer.service.consumerService.controller;


import lombok.var;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    UserRestConsumer restConsumer;

    @Autowired
    AuthenticationConsumer authenticationConsumer;



    @PostMapping("/login")
    String login(@RequestBody Map<String, Object> map){

        String initial_response =  restConsumer.login(map);

        // if name not found || password is incorrect
       if(!initial_response.contains("data"))
        return initial_response;

        // else insert token with the initial response
        int id_index = initial_response.indexOf("id")+5;
        String id = initial_response.substring(  id_index,
                                                    initial_response.indexOf(",", id_index));
      //  System.out.println(id);

        var objectId = new ObjectId(id);
        String token = createToken(objectId);

        StringBuilder response = new StringBuilder(initial_response);
        int token_index = initial_response.indexOf('}', id_index)-4;
        response.insert(token_index, ",\n"+"       token : "+token);

        return response.toString();

    }
    @GetMapping("/create-token/{id}")
    public String createToken(@PathVariable("id") ObjectId id){
        return authenticationConsumer.createToken(id);
    }

}
