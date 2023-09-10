package com.codecool.backend.configuration;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.database.Database;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
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
