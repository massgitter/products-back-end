package com.openfabric.openfabricbackend.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Country extends Common {
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String city;
}
