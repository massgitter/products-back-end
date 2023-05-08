package com.openfabric.openfabricbackend.models;

import com.openfabric.openfabricbackend.responses.ProductResponse;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends Common {
    private String name;
    private String color;
    private int size;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Manufacturer manufacturer;

    public ProductResponse productResponse() {
        return ProductResponse.builder()
                .id(getId())
                .name(name)
                .color(color)
                .size(size)
                .category(category.getName())
                .company(manufacturer.getName())
                .phone(manufacturer.getPhone())
                .email(manufacturer.getEmail())
                .city(manufacturer.getCountry().getCity())
                .country(manufacturer.getCountry().getName())
                .build();
    }
}
