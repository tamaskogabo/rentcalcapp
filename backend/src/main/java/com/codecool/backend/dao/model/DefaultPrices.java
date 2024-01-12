package com.codecool.backend.dao.model;

public enum DefaultPrices {
    BASE_RENT(170000),
    KKT(25350),
    INTERNET(7000),
    GAS(5635);

    final int price;

    DefaultPrices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
