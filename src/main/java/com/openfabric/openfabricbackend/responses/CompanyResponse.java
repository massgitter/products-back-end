package com.openfabric.openfabricbackend.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {
    private long id;
    private String name;
    private String phone;
    private String email;
    private String country;
    private String city;
}
