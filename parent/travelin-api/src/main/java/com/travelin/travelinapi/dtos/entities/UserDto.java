package com.travelin.travelinapi.dtos.entities;

import com.travelin.travelinapi.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/31/2022, Sat
 **/


@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class UserDto implements Serializable {

    private String name;

    private String password;

    private String username;

    private String email;

    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;
        return getName().equals(userDto.getName()) && getPassword().equals(userDto.getPassword()) && getUsername().equals(userDto.getUsername()) && getEmail().equals(userDto.getEmail()) && getRole() == userDto.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPassword(), getUsername(), getEmail(), getRole());
    }
}
