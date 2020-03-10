package com.example.restfulservices2.restfulservices2.controller;

import com.example.restfulservices2.restfulservices2.model.DynamicFilteringUser;
import com.example.restfulservices2.restfulservices2.service.DynamicFilteringService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicFilteringController {

    @Autowired
    private DynamicFilteringService dynamicFilteringService;

    @PostMapping(path = "/dynamicadduser")
    public MappingJacksonValue addUser(@RequestBody DynamicFilteringUser user){
        DynamicFilteringUser user1 = dynamicFilteringService.addUser(user);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username");

        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFiltering",filter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user1);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
        }
    }

