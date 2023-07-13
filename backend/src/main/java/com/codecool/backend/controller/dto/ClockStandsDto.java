package com.codecool.backend.controller.dto;

public record ClockStandsDto(double warmWaterStand,
                             double coldWaterStand,
                             int electricityStand,
                             int warmingBill,
                             int gasBill) {
}
