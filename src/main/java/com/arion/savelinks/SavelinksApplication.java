package com.arion.savelinks;

import com.arion.savelinks.entity.User;
import com.arion.savelinks.repository.UserDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SavelinksApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavelinksApplication.class, args);
	}


}
