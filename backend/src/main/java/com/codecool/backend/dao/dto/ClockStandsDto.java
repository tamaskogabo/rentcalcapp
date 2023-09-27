package com.codecool.backend.dao.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClockStandsDto(int id,
                             LocalDateTime date,
                             double warmWaterStand,
                             double coldWaterStand,
                             int electricityStand,
                             int warmingBill,
                             int gasBill) {
}
