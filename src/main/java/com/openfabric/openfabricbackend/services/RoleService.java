package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Role;
import com.openfabric.openfabricbackend.repositories.RoleRepo;
import com.openfabric.openfabricbackend.utils.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public void create(RoleEnum name) {
        if (roleRepo.findByName(name) == null) {
            Role role = Role.builder()
                    .name(name)
                    .build();
            roleRepo.save(role);
        }
    }

    public void onInit() {
        Arrays.stream(RoleEnum.values()).forEach(this::create);
    }
}
