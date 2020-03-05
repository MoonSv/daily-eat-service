package com.daily.eat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DailyEatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyEatApplication.class, args);
	}

}
