package com.openfabric.openfabricbackend.repositories;

import com.openfabric.openfabricbackend.models.Role;
import com.openfabric.openfabricbackend.utils.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(RoleEnum roleEnum);
}
