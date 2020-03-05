package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionService implements ExecuteService{

//    @Autowired                      //bean injection
    DemoService demoService;

//    @Autowired                      //bean injection method 2 by constructor
//    SessionService(DemoService demoService){
//        this.demoService=demoService;
//    }

    @Autowired                      //by setter method
    void setDemoService(DemoService demoService){
        this.demoService=demoService;
    }

    public void createSession(){
        System.out.println("session created");
        demoService.createDemo();
    }

    @Override
    public void executeDemo() {
        System.out.println("demosession created....");
    }
}
