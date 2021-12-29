package com.project.springdockerpostgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringDockerPostgresApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDockerPostgresApplication.class, args);
	}
}
