package com.openfabric.openfabricbackend.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenRefreshResponse {
    private String token;
    private String refreshToken;
}
