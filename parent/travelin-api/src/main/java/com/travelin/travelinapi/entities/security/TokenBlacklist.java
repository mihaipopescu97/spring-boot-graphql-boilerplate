package com.travelin.travelinapi.entities.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/27/2022, Tue
 **/

@Entity @Table(name = "TOKEN_BLACKLIST", schema = "travelin")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class TokenBlacklist {

    @Id
    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TokenBlacklist that)) return false;
        return getToken().equals(that.getToken()) && getExpiryDate().equals(that.getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getExpiryDate());
    }
}
