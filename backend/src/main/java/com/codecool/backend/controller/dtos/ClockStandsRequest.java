package com.codecool.backend.controller.dtos;

import com.codecool.backend.dao.model.ClockStands;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class ClockStandsRequest {
    @NotNull(message = "warmWaterStand value missing in request, or less than 50")
    @Min(value = 50)
    private final double warmWaterStand;
    @NotNull(message = "coldWaterStand value missing in request, or less than 50")
    @Min(value = 50)
    private final double coldWaterStand;
    @NotNull(message = "electricityStand value missing in request, or less than 50")
    @Min(value = 50)
    private final int electricityStand;
    @NotNull(message = "warmingBill value missing in request, or less than 50")
    @Min(value = 50)
    private final int warmingBill;

    public ClockStandsRequest(double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill) {
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
    }

    public ClockStands createClockStands() {
        return ClockStands.of(LocalDateTime.now(), warmWaterStand, coldWaterStand, electricityStand, warmingBill);
    }

    @Override
    public String toString() {
        return "ClockStandsRequest{" +
                "warmWaterStand=" + warmWaterStand +
                ", coldWaterStand=" + coldWaterStand +
                ", electricityStand=" + electricityStand +
                ", warmingBill=" + warmingBill +
                '}';
    }
}
