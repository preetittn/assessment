package com.example.restfulservices2.restfulservices2.controller;

import com.example.restfulservices2.restfulservices2.model.BasicUser;
import com.example.restfulservices2.restfulservices2.model.DetailedUser;
import com.example.restfulservices2.restfulservices2.service.BasicAndDetailedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VersioningController {

    @Autowired
    private BasicAndDetailedUserService basicAndDetailedUserService;

    //uri based versioning
    @GetMapping(path = "/v1/basic")
    public List<BasicUser> basicDetails(){
        return basicAndDetailedUserService.getAllBasicUser();
    }

    @GetMapping(path = "/v2/detailed")
    public List<DetailedUser> allDetails(){
        return basicAndDetailedUserService.getAllDetailedUser();
    }

    //Request Parameter Versioning
    @GetMapping(value = "/basic/params",params = "version=1")
    public List<BasicUser> basicDetailsUsingParams(){
        return basicAndDetailedUserService.getAllBasicUser();
    }

    @GetMapping(value = "/detailed/params",params = "version=2")
    public List<DetailedUser> allDetailsUsingParams(){
        return basicAndDetailedUserService.getAllDetailedUser();
    }

    //header versioning
    @GetMapping(value = "/user/header",headers = "version=1")
    public List<BasicUser> basicDetailsUsingHeaders(){
        return basicAndDetailedUserService.getAllBasicUser();
    }

    @GetMapping(value = "/user/header",headers = "version=2")
    public List<DetailedUser> allDetailsUsingHeaders(){
        return basicAndDetailedUserService.getAllDetailedUser();
    }

    //MimeType versioning
    @GetMapping(value = "/user/produces",produces = "application/version1+json")
    public List<BasicUser> basicDetailsUsingMime(){
        return basicAndDetailedUserService.getAllBasicUser();
    }

    @GetMapping(value = "/user/produces",produces = "application/version2+json")
    public List<DetailedUser> allDetailsUsingMime(){
        return basicAndDetailedUserService.getAllDetailedUser();
    }


}
