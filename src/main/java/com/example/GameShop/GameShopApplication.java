package com.example.GameShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class GameShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameShopApplication.class, args);
	}
}
