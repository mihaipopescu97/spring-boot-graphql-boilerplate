package com.travelin.travelinapi.services.authorisation;

import com.travelin.travelinapi.components.security.JwtUtils;
import com.travelin.travelinapi.dtos.tokens.jwt.JwtRequest;
import com.travelin.travelinapi.dtos.tokens.jwt.JwtResponse;
import com.travelin.travelinapi.dtos.tokens.refresh.RefreshTokenRequest;
import com.travelin.travelinapi.dtos.tokens.refresh.RefreshTokenResponse;
import com.travelin.travelinapi.entities.User;
import com.travelin.travelinapi.entities.security.RefreshToken;
import com.travelin.travelinapi.exceptions.TokenRefreshException;
import com.travelin.travelinapi.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Service
@Log4j2
public class AuthenticationService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public JwtResponse getJwtToken(final JwtRequest jwtRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
        );

        final Optional<User> userOptional = userRepository.findByUsername(jwtRequest.getUsername());

        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            final RefreshToken refreshToken = createRefreshToken(user);
            return new JwtResponse(
                    jwtUtils.generateToken(user),
                    refreshToken.getToken(),
                    user.getId(),
                    user.getUsername(),
                    user.getRole());
        }
        return null;
    }

    private RefreshToken createRefreshToken(final User user) {
        return refreshTokenService.createRefreshToken(user);
    }
    public RefreshTokenResponse getRefreshToken(final RefreshTokenRequest request) {
        final String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.getRefreshToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    final String token = jwtUtils.generateToken(user);

                    return new RefreshTokenResponse(token, requestRefreshToken);
                }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not available"));
    }

    public void invalidate(final String token) {
        jwtUtils.invalidateToken(token);
    }

}
