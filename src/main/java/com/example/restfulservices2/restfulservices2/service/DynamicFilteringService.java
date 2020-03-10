package com.example.restfulservices2.restfulservices2.service;

import com.example.restfulservices2.restfulservices2.model.BasicUser;
import com.example.restfulservices2.restfulservices2.model.DetailedUser;
import com.example.restfulservices2.restfulservices2.model.DynamicFilteringUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DynamicFilteringService {
    private static List<DynamicFilteringUser> userList = new ArrayList<>();

    public DynamicFilteringUser addUser(DynamicFilteringUser user) {
        userList.add(user);
        return user;
    }
}
