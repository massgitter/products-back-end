package com.openfabric.openfabricbackend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequest {
    @NotNull String username;
    @NotNull String password;
}
