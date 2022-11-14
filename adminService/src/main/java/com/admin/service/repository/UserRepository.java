package com.admin.service.repository;

import com.admin.service.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {


    /*@Modifying
    @Query("SELECT u FROM User u WHERE u.name = :name")
    List<User> loginUser(@Param("name") String name);*/

    @Query("{name:?0}")
    public User loginUser(String name);

}
