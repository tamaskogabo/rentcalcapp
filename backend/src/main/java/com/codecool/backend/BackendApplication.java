package com.codecool.backend;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public ClockStandsDao clockStandsDao() {
        return new ClockStandsDaoJDBC();
    }
    @Bean
    public ClockStandsService clockStandsService() {
        return new ClockStandsService(clockStandsDao());
    }
}
