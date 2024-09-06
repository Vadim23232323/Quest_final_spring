package com.example.quest_spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		log.info("------------------------------------------------------------------------------");
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
