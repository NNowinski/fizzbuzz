package fr.nowinski.fizzbuzz.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * application main class
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "fr.nowinski.fizzbuzz" })
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

}
