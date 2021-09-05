package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
@Controller
@SpringBootApplication

@ServletComponentScan(basePackages = {"com.example.demo.filter"})
public class HellowSpringBoot1Application extends SpringBootServletInitializer{
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HellowSpringBoot1Application.class);
    }
	
	public static void main(String[] args) throws Exception{
		SpringApplication.run(HellowSpringBoot1Application.class, args);
	}
}
