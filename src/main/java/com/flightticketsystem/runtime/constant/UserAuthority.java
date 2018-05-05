package com.flightticketsystem.runtime.constant;

public enum UserAuthority {

    NORMAL(0), ADMIN(1);

    private Integer userAuthority;

    public Integer getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(Integer userAuthority) {
        this.userAuthority = userAuthority;
    }

    UserAuthority(Integer userAuthority) {
        this.userAuthority = userAuthority;
    }
}
