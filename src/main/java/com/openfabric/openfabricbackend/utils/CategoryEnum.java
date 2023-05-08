package com.openfabric.openfabricbackend.utils;

public enum CategoryEnum {
    SOFTWARE("Software"),
    FRUIT("Fruit"),
    GRAIN("Grain"),
    AIRPLANE("Airplane"),
    ELECTRONICS("Electronics"),
    FURNITURE("Furniture"),
    MACHINERY("Machinery"),
    AUTOMOBILE("Automobile"),
    FUEL("Fuel"),
    COSMOTICS("Cosmotics"),
    FASHION("Fashion");

    private final String code;

    CategoryEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
