package com.openfabric.openfabricbackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String phone;
    private String email;
    private Long country;
}
