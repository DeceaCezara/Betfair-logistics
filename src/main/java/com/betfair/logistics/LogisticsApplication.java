package com.betfair.logistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //enables spring's ability to run async tasks(usually used for main class)
public class LogisticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticsApplication.class, args);
	}

}
