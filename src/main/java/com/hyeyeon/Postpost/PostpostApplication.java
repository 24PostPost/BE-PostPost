package com.hyeyeon.Postpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PostpostApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostpostApplication.class, args);
	}

}
