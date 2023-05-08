package com.openfabric.openfabricbackend.models;

import com.openfabric.openfabricbackend.responses.UserLoginResponse;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Users extends Common implements UserDetails {
    private String username;
    private String password;
    private String fullName;
    private boolean enabled = true;
    @ManyToOne
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getName().getCode()));
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    public UserLoginResponse toUserLoginResponse() {
        UserLoginResponse response=new UserLoginResponse();
        response.setId(this.getId());
        response.setUsername(username);
        response.setFullName(fullName);
        response.setRole(this.role.getName().getCode());

        return response;
    }
}
