package com.auth.gestaoTarefas.api.models;

import com.auth.gestaoTarefas.api.models.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserModel implements UserDetails, Serializable {

    private static final long Seria = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String Password;

    @Column(nullable = false)
    private UserRole role;

    public UserModel(String name, String password, UserRole role) {
        this.name = name;
        Password = password;
        this.role = role;
    }

    public UserModel() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
