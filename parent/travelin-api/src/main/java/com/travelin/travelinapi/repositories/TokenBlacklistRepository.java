package com.travelin.travelinapi.repositories;

import com.travelin.travelinapi.entities.security.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/27/2022, Tue
 **/

@Repository
public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, String> {

    Optional<TokenBlacklist> findByToken(final String token);
}
