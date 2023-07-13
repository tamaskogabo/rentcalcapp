package com.codecool.backend.service;

import com.codecool.backend.controller.dto.ClockStandsDto;
import com.codecool.backend.dao.ClockStandsDao;
import com.codecool.backend.dao.model.ClockStands;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
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
}
