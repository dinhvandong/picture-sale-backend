package com.adda.picture_sale_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.adda.picture_sale_backend")
@EnableMongoRepositories(basePackages = "com.adda.picture_sale_backend.repositories")
@EnableScheduling
class PictureSaleBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(PictureSaleBackendApplication.class, args);
	}

}
