package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.RefreshToken;
import com.openfabric.openfabricbackend.repositories.RefreshTokenRepo;
import com.openfabric.openfabricbackend.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${openfabric.app.jwtRefreshExpirationSec}")
    private Long refreshTokenDurationSec;

    private final RefreshTokenRepo refreshTokenRepo;

    private final UsersRepo usersRepo;

    public RefreshTokenService(RefreshTokenRepo refreshTokenRepo, UsersRepo usersRepo) {
        this.refreshTokenRepo = refreshTokenRepo;
        this.usersRepo = usersRepo;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(usersRepo. findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(refreshTokenDurationSec));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            throw new RuntimeException("Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepo.deleteByUser(usersRepo.findById(userId).get());
    }
}
