package com.openfabric.openfabricbackend.responses;

import com.openfabric.openfabricbackend.utils.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
    private RoleEnum role;
    private long partyId;
}
