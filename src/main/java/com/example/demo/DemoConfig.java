package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DemoConfig {
    @Bean
    DemoService demoService(){
        return new DemoService();
    }

    @Bean
    public ExecuteService getDemoService()
    {
        return new DemoService();
    }

    @Bean
    @Primary
    public ExecuteService getSessionService()
    {
        return new SessionService();
    }
}
