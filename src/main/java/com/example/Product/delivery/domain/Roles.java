package com.example.Product.delivery.domain;

import java.util.EnumSet;
import java.util.Set;

public enum Roles {
    ADMIN(EnumSet.of(
            Permission.USER_READ,
            Permission.PRODUCT_READ,
            Permission.REGION_CREATE,
            Permission.REGION_EDIT,
            Permission.REGION_DELETE,
            Permission.PLACE_CREATE,
            Permission.PLACE_EDIT,
            Permission.PLACE_DELETE,
            Permission.OFFER_READ,
            Permission.REQUEST_READ,
            Permission.TRANSACTION_CREATE,
            Permission.TRANSACTION_READ,
            Permission.TRANSACTION_UPDATE,
            Permission.TRANSACTION_DELETE,
            Permission.STATISTICS
    )),
    EDITOR(EnumSet.of(
            Permission.USER_READ,
            Permission.PRODUCT_READ,
            Permission.REGION_CREATE,
            Permission.REGION_EDIT,
            Permission.PLACE_CREATE,
            Permission.PLACE_EDIT,
            Permission.OFFER_READ,
            Permission.REQUEST_READ
    )),
    READER(EnumSet.of(
            Permission.USER_READ,
            Permission.PRODUCT_READ,
            Permission.OFFER_READ,
            Permission.REQUEST_READ
    )),
    CARRIER(EnumSet.of(
            Permission.OFFER_READ,
            Permission.REQUEST_READ,
            Permission.TRANSACTION_CREATE,
            Permission.TRANSACTION_READ,
            Permission.TRANSACTION_UPDATE,
            Permission.TRANSACTION_DELETE
    ));

    private final Set<Permission> permissions;

    Roles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}

