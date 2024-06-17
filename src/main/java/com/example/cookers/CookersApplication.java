package com.example.cookers;

import com.example.cookers.domain.member.service.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CookersApplication {
	public static void main(String[] args) {
		SpringApplication.run(CookersApplication.class, args);
	}
}
