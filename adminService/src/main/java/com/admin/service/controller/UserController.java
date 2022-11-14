package com.admin.service.controller;

import com.admin.service.model.User;
import com.admin.service.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService service;


    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping(value = "/registeration")
    public String signup(@RequestBody User user){
        return service.signupService(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Map<String, Object> map){
        return service.loginService(map.get("name").toString(), map.get("password").toString());
    }


}
