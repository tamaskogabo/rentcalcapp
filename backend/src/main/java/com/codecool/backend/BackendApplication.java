package com.codecool.backend;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.database.Database;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
