package saru.saru_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class SaruRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaruRestApplication.class, args);
	}

}
