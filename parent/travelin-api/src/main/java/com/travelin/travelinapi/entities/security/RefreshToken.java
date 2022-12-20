package com.travelin.travelinapi.entities.security;

import com.travelin.travelinapi.entities.User;
import com.travelin.travelinapi.entities.generics.GenericEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Entity @Table(name = "REFRESH_TOKEN", schema = "travelin")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RefreshToken extends GenericEntity {

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "EXPIRY_DATE", nullable = false)
    private LocalDateTime expiryDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefreshToken that)) return false;
        if (!super.equals(o)) return false;
        return getUser().equals(that.getUser()) && getToken().equals(that.getToken()) && getExpiryDate().equals(that.getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser(), getToken(), getExpiryDate());
    }
}
