package com.codecool.backend.configuration;

import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.ClockStandsDaoJDBC;
import com.codecool.backend.database.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DaoConfig {
    @Bean
    public Database database() {
        return Database.of(System.getenv("databaseURL"), System.getenv("databaseUsername"), System.getenv("databasePassword"));
    }
    @Bean
    public ClockStandsDao clockStandsDao() throws SQLException {
        return new ClockStandsDaoJDBC(database());
    }
}
