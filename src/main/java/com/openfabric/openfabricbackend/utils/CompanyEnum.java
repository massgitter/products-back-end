package com.openfabric.openfabricbackend.utils;

public enum CompanyEnum {
    BOEING("Boeing", "+251936532541", "boeing@gmail.com", 1L),
    ALIBABA("Alibaba", "+251948759612", "alibaba@gmail.com", 2L),
    GAZPROM("Gazprom", "+261542103625", "gazprom@gmail.com", 3L),
    METINVEST("Metinvest", "+251935624218", "metinvest@gmail.com", 4L),
    ASTRAZENICA("Astra Zenica", "+251914785124", "astrazenica@gmail.com", 5L),
    SIEMENS("Siemens", "+251936541230", "siemens@gmail.com", 6L),
    TOTALENERGIES("Total Energies", "+251636254102", "totalenergies@gmail.com", 7L),
    ASSICURAZIONI("Assicurazioni", "+251936254875", "assicurazioni@gmail.com", 8L);

    private final String name;
    private final String phone;
    private final String email;
    private final Long country;

    CompanyEnum(String name, String phone, String email, Long country) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getCountry() {
        return country;
    }
}
