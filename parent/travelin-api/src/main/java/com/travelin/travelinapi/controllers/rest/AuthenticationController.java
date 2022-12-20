package com.travelin.travelinapi.controllers.rest;

import com.travelin.travelinapi.dtos.tokens.jwt.JwtRequest;
import com.travelin.travelinapi.dtos.tokens.jwt.JwtResponse;
import com.travelin.travelinapi.dtos.tokens.refresh.RefreshTokenRequest;
import com.travelin.travelinapi.dtos.tokens.refresh.RefreshTokenResponse;
import com.travelin.travelinapi.services.authorisation.AuthenticationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/25/2022, Sun
 **/

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest request) {
       final JwtResponse response = authenticationService.getJwtToken(request);

       if (response != null) {
           return ResponseEntity.ok(response);
       }
       return ResponseEntity.badRequest().body("Cannot create jwt token with the provided data");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody final RefreshTokenRequest request) {
        final RefreshTokenResponse response = authenticationService.getRefreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/blacklist")
    public ResponseEntity<?> blacklist(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authorization) {
       authenticationService.invalidate(authorization.split("Bearer ")[1]);
       return ResponseEntity.ok("Invalidation successful");
    }
}
