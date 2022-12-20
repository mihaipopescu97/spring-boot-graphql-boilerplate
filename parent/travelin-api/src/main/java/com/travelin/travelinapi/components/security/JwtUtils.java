package com.travelin.travelinapi.components.security;

import com.travelin.travelinapi.entities.security.TokenBlacklist;
import com.travelin.travelinapi.repositories.TokenBlacklistRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/25/2022, Sun
 **/
@Component
public class JwtUtils {

    @Autowired
    private Environment environment;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    public String extractUsername(final String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean hasClaim(final String token, final String claimName) {
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(final String token) {
        final String jwtSecretKey = environment.getProperty("JWT_SECRET");
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(final UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public String generateToken(final UserDetails userDetails, final Map<String, Object> claims) {
        return createToken(claims, userDetails);
    }

    private String createToken(final Map<String, Object> claims, final UserDetails userDetails) {
        final String jwtSecretKey = environment.getProperty("JWT_SECRET");
        final long jwtDuration = Long.parseLong(Objects.requireNonNull(environment.getProperty("TOKEN_DURATION")));
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(jwtDuration)))
                .signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
    }

    public Boolean isTokenValid(final String token, final UserDetails userDetails) {
        final String username = extractUsername(token);
        final Optional<TokenBlacklist> tokenBlacklist = tokenBlacklistRepository.findByToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && tokenBlacklist.isEmpty();
    }

    public void invalidateToken(final String token) {
        final LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(15);
        tokenBlacklistRepository.save(new TokenBlacklist(token, expiryDate));
    }
}
