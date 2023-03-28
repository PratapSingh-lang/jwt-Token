package com.jwt.Authentiaction.project.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan
public class ProjectLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectLoginApplication.class, args);
	}

}
