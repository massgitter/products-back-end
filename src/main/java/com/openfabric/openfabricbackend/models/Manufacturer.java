package com.openfabric.openfabricbackend.models;

import com.openfabric.openfabricbackend.responses.CompanyResponse;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Manufacturer extends Common {
    @Column(unique = true)
    private String name;
    private String phone;
    @Column(unique = true)
    @Email
    private String email;
    @ManyToOne
    private Country country;

    public CompanyResponse companyResponse() {
        return CompanyResponse.builder()
                .id(getId())
                .name(name)
                .phone(phone)
                .email(email)
                .country(country.getName())
                .city(country.getCity())
                .build();
    }
}
