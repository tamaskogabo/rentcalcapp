package com.codecool.backend.dao;

import com.codecool.backend.dao.model.ClockStands;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.time.Month;
import java.util.List;

public interface ClockStandsDao {
    public List<ClockStands> getAllClockStands() throws SQLException;
    public List<ClockStands> getClockStandByMonthAndYear(int year, Month month) throws SQLException;
    public boolean postClockStand(com.codecool.backend.dao.model.ClockStands clockStands) throws SQLException;
    public void deleteClockStandByYearAndMonth(int year, Month month) throws SQLException;
}
