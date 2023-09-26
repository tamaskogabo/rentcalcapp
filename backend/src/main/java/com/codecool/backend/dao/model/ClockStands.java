package com.codecool.backend.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.codecool.backend.dao.model.DefaultPrices.*;

@Getter
public class ClockStands {
    private final LocalDateTime dateTime;
    private final double warmWaterStand;
    private final double coldWaterStand;
    private final int electricityStand;
    private final int warmingBill;
    private final int gasBill = GAS.price;
    private final int baseRent = BASE_RENT.price;
    private final int kkt = KKT.price;
    private final int internetCost = INTERNET.price;

    public ClockStands(LocalDateTime dateTime, double warmWaterStand, double coldWaterStand, int electricityStand, int warmingBill, int gasBill) {
        this.dateTime = dateTime;
        this.warmWaterStand = warmWaterStand;
        this.coldWaterStand = coldWaterStand;
        this.electricityStand = electricityStand;
        this.warmingBill = warmingBill;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
