package com.unisystems.registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistryApplication implements CommandLineRunner {

	@Autowired
	MockDataManager mockDataManager;

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}

	@Override
	public void run(String... args) {
		mockDataManager.generateAndSaveMockData();
	}
}
