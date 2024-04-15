package com.ekhunt.backend;

import com.ekhunt.backend.model.Filter;
import com.ekhunt.backend.repository.FilterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner testData(FilterRepository repository) {
		return args -> {
			List<Filter> testData = new ArrayList<>();
			testData.add(new Filter("Amount", "Higher", "23"));
			testData.add(new Filter("Title", "Contains", "Test"));
			testData.add(new Filter("Date", "From", "01.01.2024"));
			repository.saveAll(testData);
		};
	}

}
