package com.travelin.travelinapi.repositories;

import com.travelin.travelinapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(final String username);

    Optional<User> findByEmail(final String email);
}
