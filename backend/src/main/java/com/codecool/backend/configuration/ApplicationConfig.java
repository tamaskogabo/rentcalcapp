package com.codecool.backend.configuration;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.database.Database;
import com.codecool.backend.service.ClockStandsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class ApplicationConfig {
    @Bean
    public Database database() {
        return new Database(System.getenv("databaseURL"), System.getenv("databaseUsername"), System.getenv("databasePassword"));
    }
    @Bean
    public ClockStandsDao clockStandsDao() throws SQLException {
        return new ClockStandsDaoJDBC(database());
    }
    @Bean
    public ClockStandsService clockStandsService() throws SQLException {
        return new ClockStandsService(clockStandsDao());
    }
}
