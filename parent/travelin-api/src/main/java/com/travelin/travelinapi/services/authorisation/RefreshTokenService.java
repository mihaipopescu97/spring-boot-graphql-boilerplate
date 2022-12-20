package com.travelin.travelinapi.services.authorisation;

import com.travelin.travelinapi.entities.security.RefreshToken;
import com.travelin.travelinapi.entities.User;
import com.travelin.travelinapi.exceptions.TokenRefreshException;
import com.travelin.travelinapi.repositories.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Service
public class RefreshTokenService {

    @Autowired
    private Environment environment;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshToken> getRefreshToken(final String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(final User user) {
        final long refreshTokenDuration = Long.parseLong(Objects.requireNonNull(environment.getProperty("REFRESH_TOKEN_DURATION")));
        final RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusMinutes(refreshTokenDuration));

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signIn request");
        }
        return token;
    }
}
