package com.admin.service;

import com.admin.service.model.User;
import com.admin.service.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AdminServiceApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}

	private final UserRepository userRepository;


	public AdminServiceApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(new User("Alice","alice1234@gmail.com","1234","1234"));
		userRepository.save(new User("Bob","bob4356@gmail.com","12345","12345"));
		userRepository.save(new User("Carol",null,"123456","123456"));

		userRepository.findAll().forEach(System.out::println);
	}
}
