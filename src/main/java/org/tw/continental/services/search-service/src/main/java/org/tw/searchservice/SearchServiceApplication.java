package org.tw.searchservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SearchServiceApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);

		springApplication.run( args);
	}

}
