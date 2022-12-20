package com.travelin.travelinapi.dtos.tokens.jwt;

import com.travelin.travelinapi.enums.Role;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Data
@ToString
public class JwtResponse implements Serializable {

    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private Long userId;
    private String username;
    private Role role;

    public JwtResponse(final String token, final String refreshToken, final Long userId, final String username, final Role role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtResponse that)) return false;
        return getToken().equals(that.getToken()) && getType().equals(that.getType()) && getRefreshToken().equals(that.getRefreshToken()) && getUserId().equals(that.getUserId()) && getUsername().equals(that.getUsername()) && getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getType(), getRefreshToken(), getUserId(), getUsername(), getRole());
    }
}
