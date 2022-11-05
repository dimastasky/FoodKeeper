package com.dimastasky.foodkeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FoodKeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodKeeperApplication.class, args);
	}
}
