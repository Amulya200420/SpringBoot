package com.klef.jfsd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.klef.jfsd.springboot")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.print("SDP Project 4");
	}

}
