package com.example.springlogging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringLoggingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringLoggingApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("APPLICATION STARTED");
	}

}
