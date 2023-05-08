package com.openfabric.openfabricbackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;
}
