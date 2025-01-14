package com.denys.travel_agency.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.denys.travel_agency.enums.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(ADMIN_CREATE, ADMIN_DELETE, ADMIN_READ, ADMIN_UPDATE)),
    MANAGER(Set.of(MANAGER_READ, MANAGER_UPDATE, MANAGER_CREATE, MANAGER_DELETE)),
    USER(Set.of(USER_READ, USER_UPDATE, USER_CREATE, USER_DELETE));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
