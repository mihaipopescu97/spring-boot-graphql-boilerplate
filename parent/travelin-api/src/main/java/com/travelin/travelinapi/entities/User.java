package com.travelin.travelinapi.entities;

import com.travelin.travelinapi.entities.generics.GenericEntity;
import com.travelin.travelinapi.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/
@Entity @Table(name = "USER", schema = "travelin")
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class User extends GenericEntity implements UserDetails {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "IS_EXPIRED", nullable = false)
    private boolean isExpired = false;

    @Column(name = "IS_LOCKED", nullable = false)
    private boolean isLocked = false;

    @Column(name = "IS_CREDENTIALS_EXPIRED", nullable = false)
    private boolean isCredentialsExpired = false;

    @Column(name = "IS_ENABLED", nullable = false)
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.getRole().getValue()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
        return isExpired() == user.isExpired() && isLocked() == user.isLocked() && isCredentialsExpired() == user.isCredentialsExpired() && isEnabled() == user.isEnabled() && getName().equals(user.getName()) && getPassword().equals(user.getPassword()) && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail()) && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getPassword(), getUsername(), getEmail(), getRole(), isExpired(), isLocked(), isCredentialsExpired(), isEnabled());
    }
}
