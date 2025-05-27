package com.ssafy.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssafy.web")
@SpringBootApplication
public class HomeSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeSpringApplication.class, args);
	}

}
