package com.example.restfulservices2.restfulservices2.service;

import com.example.restfulservices2.restfulservices2.model.StaticFilteringUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaticFilteringService {
    private static List<StaticFilteringUser> userList = new ArrayList<>();

    public StaticFilteringUser addUser(StaticFilteringUser user) {
       userList.add(user);
        return user;
    }
}