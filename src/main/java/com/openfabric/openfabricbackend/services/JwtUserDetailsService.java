package com.openfabric.openfabricbackend.services;

import com.openfabric.openfabricbackend.models.Users;
import com.openfabric.openfabricbackend.repositories.RoleRepo;
import com.openfabric.openfabricbackend.repositories.UsersRepo;
import com.openfabric.openfabricbackend.utils.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private UsersRepo userRepository;
    private PasswordEncoder bcryptEncoder;
    final JwtEncoder jwtEncoder;
    private RoleRepo roleRepository;

    //Act Default Username and password
    @Value("${user.username}")
    private String username;

    @Value("${user.fullName}")
    private String fullname;

    @Value("${user.password}")
    private String password;

    //jwt default expiration and issuer
    @Value("${openfabric.app.jwtExpirationSec}")
    private Long expiry;

    @Value("${openfabric.app.issuer}")
    private String issuer;
    @Autowired
    public JwtUserDetailsService(UsersRepo userRepository,
                                 PasswordEncoder bcryptEncoder,
                                 RoleRepo roleRepository,
                                 JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.roleRepository=roleRepository;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));
    }

    public void onInit() {
        Optional<Users> optionalUser=this.userRepository.findByUsername(username);
        if(optionalUser.isPresent())
            return;
        Users user= new Users();
        user.setUsername(username);
        user.setFullName(fullname);
        user.setEnabled(true);
        user.setPassword(bcryptEncoder.encode(password));
        user.setRole(roleRepository.findByName(RoleEnum.ADMIN));
        userRepository.save(user);
    }

    public String generateToken(Users user, Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds((long)expiry))
                .subject(format("%s,%s", user.getId(), user.getUsername()))
                .claim("roles", scope)
                .build();

        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return token;
    }

}
