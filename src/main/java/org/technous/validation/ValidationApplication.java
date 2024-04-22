package org.technous.validation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.technous.validation.audit.AuditorAwareImpl;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class ValidationApplication {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	public static void main(String[] args) {
		SpringApplication.run(ValidationApplication.class, args);
	}
	//   http://localhost:8091/swagger-ui/index.html#/order-controller/getOrderById
}
