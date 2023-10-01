package com.example.springvalidation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringValidationApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringValidationApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("APPLICATION STARTED");
	}

}
