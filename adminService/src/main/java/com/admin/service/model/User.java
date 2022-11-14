package com.admin.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "users")
public class User {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;

    private String email;
    private String password1;
    private String password2;

    public User(String name,String password1,String password2) {

        this.name = name;
        this.password1 = password1;
        this.password2 = password2;
    }  public User(String name,String email,String password1,String password2) {

        this.name = name;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
    }
}
