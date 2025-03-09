package com.myspring.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.myspring.demo.data.MemberRepository;
import com.myspring.demo.model.Member;

@SpringBootApplication
//@EnableJpaRepositories
public class KitchensinkApplicationWithMongo {
	public static void main(String[] args) {
		SpringApplication.run(KitchensinkApplicationWithMongo.class, args);
	}

	@Bean
	CommandLineRunner init(MemberRepository repository) {
		return args -> {
			repository.save(new Member("John Doe", "john@example.com", "1234567890"));
			repository.save(new Member("Jane Doe", "jane@example.com", "0987654321"));
		};
	}
}