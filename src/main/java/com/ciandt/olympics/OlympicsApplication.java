package com.ciandt.olympics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {OlympicsApplication.class, Jsr310JpaConverters.class})
public class OlympicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OlympicsApplication.class, args);
	}
}
