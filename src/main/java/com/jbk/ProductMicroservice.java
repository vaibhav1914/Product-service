package com.jbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductMicroservice {

	public static void main(String[] args) {
		SpringApplication.run(ProductMicroservice.class, args);
		System.out.println("Started....");
	}

}
