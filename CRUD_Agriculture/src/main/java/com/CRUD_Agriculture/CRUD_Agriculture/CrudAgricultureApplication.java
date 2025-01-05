package com.CRUD_Agriculture.CRUD_Agriculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.CRUD_Agriculture.CRUD_Agriculture")
@EnableCaching
public class CrudAgricultureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAgricultureApplication.class, args);
	}

}
