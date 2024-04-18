package com.ekhunt.backend;

import com.ekhunt.backend.model.Criteria;
import com.ekhunt.backend.model.Filter;
import com.ekhunt.backend.repository.CriteriaRepository;
import com.ekhunt.backend.repository.FilterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner testData(CriteriaRepository criteriaRepository, FilterRepository filterRepository) {
		return args -> {
			List<Criteria> criteriaTestData = new ArrayList<>();
			Filter testFilter = new Filter("New Year");
			Filter testFilter2 = new Filter("Age");
			criteriaTestData.add(new Criteria("Amount", "Higher", "23", testFilter2));
			criteriaTestData.add(new Criteria("Title", "Contains", "First", testFilter));
			criteriaTestData.add(new Criteria("Date", "From", "01.01.2024",testFilter));
			filterRepository.save(testFilter);
			filterRepository.save(testFilter2);
			criteriaRepository.saveAll(criteriaTestData);
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
