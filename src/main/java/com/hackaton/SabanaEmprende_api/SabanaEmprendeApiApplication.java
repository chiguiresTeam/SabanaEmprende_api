package com.hackaton.SabanaEmprende_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class SabanaEmprendeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabanaEmprendeApiApplication.class, args);
	}
	@GetMapping
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello World");
	}
}
