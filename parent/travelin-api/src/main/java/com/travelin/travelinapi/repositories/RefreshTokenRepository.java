package com.travelin.travelinapi.repositories;

import com.travelin.travelinapi.entities.security.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(final String token);
}
