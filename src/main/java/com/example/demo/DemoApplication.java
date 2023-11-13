package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "com.example.demo.classroom", "com.example.demo.students", "com.example.demo.utils"})//(scanBasePackages = {"com.example.demo", "com.example.demo.classroom", "com.example.demo.student", "com.example.demo.utils"}) //  used to specify the packages that should be scanned for components
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
