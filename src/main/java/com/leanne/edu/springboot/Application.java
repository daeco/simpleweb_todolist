package com.leanne.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.SQLException;

@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"com.leanne.edu.springboot"}
)
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(Application.class, args);
    }
}