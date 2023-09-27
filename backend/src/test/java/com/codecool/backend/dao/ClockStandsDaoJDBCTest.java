package com.codecool.backend.dao;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.database.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClockStandsDaoJDBCTest {

    Database db = Database.of("jdbc:postgresql://localhost:5432/postgres", "postgres", "Gm7A7Dm7");

    ClockStandsDao clockStandsDao = new ClockStandsDaoJDBC(db);

    ClockStands testClockStands = ClockStands.of(
            LocalDateTime.of(2000, Month.JANUARY,
                    1,
                    0,
                    0),
            0,
            0,
            0,
            0
    );

    ClockStandsDaoJDBCTest() throws SQLException {
    }

    @AfterEach
    void tearDown() throws SQLException {
        clockStandsDao.deleteClockStandByYearAndMonth(testClockStands.getDateTime().getYear(), testClockStands.getDateTime().getMonth());
    }

    @Test
    void getAllClockStands() throws SQLException {
        clockStandsDao.postClockStand(testClockStands);
        List<ClockStandsDto> actual = clockStandsDao.getAllClockStands();
        assertFalse(actual.isEmpty());
    }

    @Test
    void getClockStandsByMonthAndYear() throws SQLException {
        List<ClockStandsDto> actual = clockStandsDao.getClockStandByMonthAndYear(1, Month.JANUARY);
        assertTrue(actual.isEmpty());
    }
}