package com.flightticketsystem.runtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RuntimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuntimeApplication.class, args);
	}

	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
}
