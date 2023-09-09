package com.codecool.backend.dao.model;

public enum DefaultPrices {
    BASE_RENT(170000),
    KKT(25350),
    INTERNET(6500);

    final int price;

    DefaultPrices(int price) {
        this.price = price;
    }
}
