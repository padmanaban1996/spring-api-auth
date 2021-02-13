package com.smile.tech;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.smile.tech.model.Users;
import com.smile.tech.repository.UsersRepository;

@SpringBootApplication
public class SpringwithAngularCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringwithAngularCrudApplication.class, args);
	}

}
