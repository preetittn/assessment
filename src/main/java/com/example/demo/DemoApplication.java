package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext=SpringApplication.run(DemoApplication.class, args);
//		for (String name : applicationContext.getBeanDefinitionNames()){
//
//			System.out.println("this is name"+name);

		DemoService demoService=(DemoService) applicationContext.getBean("demoService");
		demoService.createDemo();

		DemoSession demoSession=(DemoSession) applicationContext.getBean("demoSession");
		demoSession.createSession();
//
//		DemoService demoService1=(DemoService) applicationContext.getBean("demoService");
//		demoService.createDemo();

		SessionService sessionService = (SessionService) applicationContext.getBean("sessionService");
		sessionService.createSession();
		demoService.createDemo();


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

		ExecuteService executeService = context.getBean(ExecuteService.class);
		executeService.executeDemo();

		TightlyCoupled tightlyCoupled = new TightlyCoupled();
		tightlyCoupled.tightlyDemo();



	}

}
