package com.ggproject.myBookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyBookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookshelfApplication.class, args);
	}

}
