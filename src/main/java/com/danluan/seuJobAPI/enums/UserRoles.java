package com.danluan.seuJobAPI.enums;

public enum UserRoles {
    ADMIN("ADMIN"),
    USER("USER"),
    WORKER("WORKER"),
    COMPANY("COMPANY"),
    FREELANCER("FREELANCER");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
