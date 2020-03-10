package com.example.restfulservices2.restfulservices2.controller;

import com.example.restfulservices2.restfulservices2.model.StaticFilteringUser;
import com.example.restfulservices2.restfulservices2.service.StaticFilteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticFilteringController {

    @Autowired
    private StaticFilteringService staticFilteringService;
  @PostMapping(path = "/staticadduser")
  public StaticFilteringUser addUser(@RequestBody StaticFilteringUser user) {
      return staticFilteringService.addUser(user);
  }

}
