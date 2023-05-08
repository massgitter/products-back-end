package com.openfabric.openfabricbackend.repositories;

import com.openfabric.openfabricbackend.models.RefreshToken;
import com.openfabric.openfabricbackend.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(Users user);
}
