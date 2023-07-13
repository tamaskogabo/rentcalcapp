package com.codecool.backend;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.database.Database;
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
    public Database database() {
        return new Database("jdbc:postgresql://localhost:5432/postgres", "postgres", System.getenv("databasePassword"));
    }
    @Bean
    public ClockStandsDao clockStandsDao() {
        return new ClockStandsDaoJDBC(database());
    }
    @Bean
    public ClockStandsService clockStandsService() {
        return new ClockStandsService(clockStandsDao());
    }
}
