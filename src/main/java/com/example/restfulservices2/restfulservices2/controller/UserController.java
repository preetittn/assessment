package com.example.restfulservices2.restfulservices2.controller;

import com.example.restfulservices2.restfulservices2.model.User;
import com.example.restfulservices2.restfulservices2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/adduser")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping(path = "/getuser")
    public List<User> getUser(){
        return userService.getAll();
    }

    @DeleteMapping(path = "/deleteuser/{name}")
    public List<User> deleteUser(@PathVariable String name){
        return userService.deleteUser(name);
    }

    //HateOS Question 11
    @GetMapping("/users/{name}")
    public EntityModel<User> getAUser(@PathVariable String name) {
        User user =  UserService.findOne(name);

        EntityModel<User> resource = new EntityModel(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUser());


        resource.add(linkTo.withRel("all-users"));


        return resource;
    }
}
