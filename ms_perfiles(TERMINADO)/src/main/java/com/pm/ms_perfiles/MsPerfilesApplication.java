package com.pm.ms_perfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPerfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPerfilesApplication.class, args);
	}

}
