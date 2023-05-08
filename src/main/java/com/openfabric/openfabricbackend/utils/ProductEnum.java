package com.openfabric.openfabricbackend.utils;

public enum ProductEnum {
    AIRPLANE("Boeing 787", "White", 100, 1L, 1L),
    PHONE("Huawei", "Black", 10, 2L, 2L),
    KEROSENE("Kerosene", "White Blue", 15, 3L, 3L),
    GRAIN("Graine", "Yellow", 50, 4L, 4L),
    CONTRACEPTION("Contraception", "White", 25, 5L, 5L),
    SIEMENS("OpenScape Voice", "Green", 23, 6L, 6L),
    FIAT("Fiat", "White", 150, 7L, 7L);

    private final String name;
    private final String color;
    private final int size;
    private final Long category;
    private final Long Manufacturer;

    ProductEnum(String name, String color, int size, Long category, Long manufacturer) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.category = category;
        Manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public Long getCategory() {
        return category;
    }

    public Long getManufacturer() {
        return Manufacturer;
    }
}
