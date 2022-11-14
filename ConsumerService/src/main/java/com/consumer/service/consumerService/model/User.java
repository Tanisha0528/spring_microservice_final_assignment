package com.consumer.service.consumerService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private ObjectId id;
    private String name;
    private String email;
    private String password1;
    private String password2;
}
