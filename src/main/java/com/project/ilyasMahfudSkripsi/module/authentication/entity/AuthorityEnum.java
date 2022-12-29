package com.project.ilyasMahfudSkripsi.module.authentication.entity;

public enum AuthorityEnum {
    ADMINISTARTION_READ("banking:read"),
    ADMINISTARTION_WRITE("banking:write");

    private final String authority;

    AuthorityEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
