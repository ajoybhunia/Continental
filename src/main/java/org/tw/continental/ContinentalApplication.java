package org.tw.continental;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContinentalApplication {
    public static void main(String[] args) {
        Class<ContinentalApplication> continentalApplicationClass = ContinentalApplication.class;
        SpringApplication springApplication = new SpringApplication(continentalApplicationClass);
        springApplication.setBannerMode(Banner.Mode.OFF);

        springApplication.run( args);
    }

}
