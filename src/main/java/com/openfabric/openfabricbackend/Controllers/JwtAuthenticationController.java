package com.openfabric.openfabricbackend.Controllers;

import com.openfabric.openfabricbackend.models.RefreshToken;
import com.openfabric.openfabricbackend.models.Users;
import com.openfabric.openfabricbackend.requests.AuthRequest;
import com.openfabric.openfabricbackend.requests.LogOutRequest;
import com.openfabric.openfabricbackend.requests.TokenRefreshRequest;
import com.openfabric.openfabricbackend.responses.TokenRefreshResponse;
import com.openfabric.openfabricbackend.responses.UserLoginResponse;
import com.openfabric.openfabricbackend.services.JwtUserDetailsService;
import com.openfabric.openfabricbackend.services.RefreshTokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authentication")
@SecurityRequirement(name = "openfabric")
@Tag(name = "Authentication")
public class JwtAuthenticationController {
    final
    AuthenticationManager authenticationManager;
    final
    JwtUserDetailsService userDetailsService;
    final JwtEncoder jwtEncoder;
    final RefreshTokenService refreshTokenService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService, JwtEncoder jwtEncoder, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtEncoder = jwtEncoder;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            Users user = (Users) authentication.getPrincipal();
            String token= this.userDetailsService.generateToken(user, authentication);

            UserLoginResponse response = user.toUserLoginResponse();
            response.setToken(token);
            response.setRefreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken());
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request, Authentication authentication) {
        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = this.userDetailsService.generateToken(user,authentication);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

}
