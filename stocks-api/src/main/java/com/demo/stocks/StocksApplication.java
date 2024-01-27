package com.demo.stocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StocksApplication {

	public static void main(String[] args) {
		System.out.println("Running ....");
		SpringApplication.run(StocksApplication.class, args);
	}

}
