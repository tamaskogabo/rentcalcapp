package com.codecool.backend.dao;

import com.codecool.backend.dao.model.ClockStands;

import java.time.Month;
import java.util.List;

public interface MonthlyClockStandsDao {
    public List<ClockStands> getAllClockStands();
    public ClockStands getClockStandByDate(int year, Month month);
    public ClockStands getClockStandsById(int id);
}
