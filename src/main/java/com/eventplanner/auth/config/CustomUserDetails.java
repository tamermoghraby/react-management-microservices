package com.eventplanner.auth.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eventplanner.auth.entity.UserCredential;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private UserCredential userCredential;

    public CustomUserDetails(UserCredential userCredential) {
        super();
        this.userCredential = userCredential;
    }
        
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(userCredential.getRole()));
    }

    @Override
    public String getPassword() {
        return userCredential.getPassword();
    }

    public String getRole() {
        return userCredential.getRole();
    }

    @Override
    public String getUsername() {
        return userCredential.getUsername();
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
}
