package com.example.restfulservices2.restfulservices2.service;

import com.example.restfulservices2.restfulservices2.model.BasicUser;
import com.example.restfulservices2.restfulservices2.model.DetailedUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BasicAndDetailedUserService {
    static List<BasicUser> basicUserList = new ArrayList<>();
    static List<DetailedUser> detailedUsers = new ArrayList<>();

    static {
        basicUserList.add(new BasicUser("Suraj", 21));
        detailedUsers.add(new DetailedUser("Suraj", 21, "Ghaziabad","9650767936", 1));
    }

    public List<BasicUser> getAllBasicUser() {
        return basicUserList;
    }


    public List<DetailedUser> getAllDetailedUser() {
        return detailedUsers;
    }
}