package org.tw.userservice;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserServiceApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);

        springApplication.run( args);
    }

}
