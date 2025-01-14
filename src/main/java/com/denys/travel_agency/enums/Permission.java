package com.denys.travel_agency.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("ADMIN_READ"), ADMIN_UPDATE("ADMIN_UPDATE"), ADMIN_CREATE("ADMIN_CREATE"), ADMIN_DELETE("ADMIN_DELETE"),
    MANAGER_READ("MANAGER_READ"), MANAGER_UPDATE("MANAGER_UPDATE"), MANAGER_CREATE("MANAGER_CREATE"), MANAGER_DELETE("MANAGER_DELETE"),
    USER_READ("USER_READ"), USER_UPDATE("USER_UPDATE"), USER_CREATE("USER_CREATE"), USER_DELETE("USER_DELETE");

    private final String permission;
}
