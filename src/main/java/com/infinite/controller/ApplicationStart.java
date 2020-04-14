package com.infinite.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class ApplicationStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(ApplicationStart.class, args);

	}

	
}
