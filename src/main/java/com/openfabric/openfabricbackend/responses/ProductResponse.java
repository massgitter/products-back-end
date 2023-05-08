package com.openfabric.openfabricbackend.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private long id;
    private String name;
    private String color;
    private int size;
    private String category;
    private String company;
    private String phone;
    private String email;
    private String city;
    private String country;
}
