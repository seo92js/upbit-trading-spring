package com.seojs.upbittradingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UpbitTradingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpbitTradingSpringApplication.class, args);
	}

}
