package com.openfabric.openfabricbackend.utils;

public enum CountryEnum {
    USA("USA", "Washington Dc"),
    CHINA("China", "Beijing"),
    RUSSIA("Russia", "Moscow"),
    UKRAIN("Ukrain", "Kiev"),
    UNITED_KINGDOM("United Kingdom", "London"),
    GERMANY("Germany", "Berlin"),
    FRANCE("France", "Paris"),
    ITALY("Italy", "Rome");

    private final String name;
    private final String city;
    CountryEnum(String country, String city) {
        this.name = country;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
