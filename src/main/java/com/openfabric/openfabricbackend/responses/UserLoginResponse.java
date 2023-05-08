package com.openfabric.openfabricbackend.responses;

import com.openfabric.openfabricbackend.utils.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginResponse {
    private long id;
    private String token;
    private String refreshToken;
    private String username;
    private String fullName;
    private String role;
}
