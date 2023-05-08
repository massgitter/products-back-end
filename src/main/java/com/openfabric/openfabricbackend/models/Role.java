package com.openfabric.openfabricbackend.models;

import com.openfabric.openfabricbackend.utils.RoleEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends Common {

    @Column(nullable = false, length = 50, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
