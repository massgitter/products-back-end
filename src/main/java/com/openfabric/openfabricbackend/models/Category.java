package com.openfabric.openfabricbackend.models;

import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends Common {
    private String name;
}
