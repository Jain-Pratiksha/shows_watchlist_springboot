package com.shows_watchlist;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Show Watchlist APIs",
				version = "1.0",
				description = "API for managing a shows watchlist",
				contact = @Contact(
						name = "Show Watchlist Team : Pratiksha"
				)
		)
)
public class ShowWatchlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowWatchlistApplication.class, args);
	}

}
