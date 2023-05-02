package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.jn")
@EnableJpaRepositories("com.jn")
@EntityScan("com.jn")
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }

}
