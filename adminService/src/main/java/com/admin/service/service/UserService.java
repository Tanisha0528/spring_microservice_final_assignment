package com.admin.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.service.repository.UserRepository;
import com.admin.service.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String signupService(User user){


        User savedUser = userRepository.save(user);
        return "{" +
                "\"message\":"+"\"Successfully Created User\",\n"+
                "\"data\":"+savedUser+",\n"+
                "}";
    }

    public String loginService(String name, String password){
        User foundUsers =  userRepository.loginUser(name);

        if(foundUsers==null){
            return "{\n" +
                    "\"message\":"+"\" Authentication Failed !!! (USER NOT FOUND) \",\n"+
                    "}";
        }

        else if(!foundUsers.getPassword1().equals(password)){
            return "{\n" +
                    "\"message\":"+"\" Password Incorrect !!!\",\n"+
                    "}";
        }

        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+"       Name : "+foundUsers.getName()+",\n"+
                "       id : "+foundUsers.getId()+",\n"+
                               "   }\n"+
                "}";

    }



}
