package com.travelin.travelinapi.dtos.tokens.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/25/2022, Sun
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtRequest implements Serializable {

    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JwtRequest that)) return false;
        return getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }
}
