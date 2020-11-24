package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
@EnableJpaAuditing
public class SpringBackendProjectPrepApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackendProjectPrepApplication.class, args);
	}

}
