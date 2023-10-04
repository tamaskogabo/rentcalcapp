package com.codecool.backend.dao;

import com.codecool.backend.dao.model.ClockStands;
import com.codecool.backend.database.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class ClockStandsDaoJDBCTest {

    Database db = Database.withSettings("jdbc:postgresql://localhost:5432/postgres", "postgres", "Gm7A7Dm7");
    ClockStandsDao clockStandsDao = new ClockStandsDaoJDBC(db);


    ClockStands testClockStands = com.codecool.backend.dao.model.ClockStands.of(
            LocalDateTime.of(2000, Month.JANUARY,
                    1,
                    0,
                    0),
            51,
            51,
            51,
            51
    );

    ClockStandsDaoJDBCTest() throws SQLException {
    }

    @AfterEach
    void tearDown() throws SQLException {
        clockStandsDao.deleteClockStandByYearAndMonth(testClockStands.getDateTime().getYear(), testClockStands.getDateTime().getMonth());
    }


    @Test
    void getAllClockStands() throws SQLException {
    // todo Pollutes DB, because the timestamp is made default by Postgres, AfterEach method won't find the test entry to delete...
        LocalDate date = LocalDate.of(2000, 1, 1);
        try (MockedStatic<LocalDate> mockedDate = mockStatic(LocalDate.class, Mockito.CALLS_REAL_METHODS)) {
            mockedDate.when(LocalDate::now).thenReturn(date);
            int clockstandsCountBeforeSave = clockStandsDao.getAllClockStands().size();
            clockStandsDao.postClockStand(testClockStands);
            List<ClockStands> actual = clockStandsDao.getAllClockStands();
            assertEquals(clockstandsCountBeforeSave + 1, actual.size());
        }
    }

    @Test
    void getClockStandsByMonthAndYear() throws SQLException {
        List<ClockStands> actual = clockStandsDao.getClockStandByMonthAndYear(1, Month.JANUARY);
        assertTrue(actual.isEmpty());
    }
}