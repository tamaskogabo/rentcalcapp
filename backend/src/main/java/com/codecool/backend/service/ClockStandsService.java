package com.codecool.backend.service;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.model.ClockStands;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ClockStandsService {
    private final ClockStandsDao clockStandsDao;

    @Autowired
    public ClockStandsService(ClockStandsDao clockStandsDao) {
        this.clockStandsDao = clockStandsDao;
    }

    public List<ClockStands> getAllClockStands() throws SQLException {
        List<ClockStandsDto> DTOs = clockStandsDao.getAllClockStands();
        return prepareClockStands(DTOs);
    }

    public List<ClockStands> getClockStandsByMonthAndYear(int year, Month month) throws SQLException {
        List<ClockStandsDto> DTOs = clockStandsDao.getClockStandByMonthAndYear(year, month);
        return prepareClockStands(DTOs);
    }

    private List<ClockStands> prepareClockStands(List<ClockStandsDto> DTOs) {
        List<ClockStands> result = new ArrayList<>();
        for (ClockStandsDto dto : DTOs) {
            result.add(new ClockStands(
                            dto.date(),
                            dto.warmWaterStand(),
                            dto.coldWaterStand(),
                            dto.electricityStand(),
                            dto.warmingBill(),
                            dto.gasBill()
                    )
            );
        }
        return result;
    }

    public boolean postClockStands(ClockStands clockStands) throws SQLException {
        return clockStandsDao.postClockStand(clockStands);
    }

}
