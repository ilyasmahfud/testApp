package com.project.ilyasMahfudSkripsi.module.authentication.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.project.ilyasMahfudSkripsi.module.authentication.entity.AuthorityEnum.*;

public enum RoleEnum {
    ADMIN(new HashSet<>(Arrays.asList(ADMINISTARTION_READ, ADMINISTARTION_WRITE))),
    USER(new HashSet<>(Arrays.asList(ADMINISTARTION_READ)));

    private final Set<AuthorityEnum> authorities;

    RoleEnum(Set<AuthorityEnum> authorities) {
        this.authorities = authorities;
    }

    public Set<AuthorityEnum> getAuthorities() {
        return authorities;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
