package com.travelin.travelinapi.enums;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/21/2022, Wed
 **/

public enum Role {

    ADMIN("Admin"),
    USER("user");

    private final String value;

    Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
