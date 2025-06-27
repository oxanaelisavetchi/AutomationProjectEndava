package com.automation.project.enums;

import lombok.Getter;

@Getter
public enum ApiPaths {
    // GET requests
    USERS_ID("users/2"),
    UNKNOWN_2("unknown/2"),
    USERS_PAGE("users?page=2"),
    UNKNOWN("unknown"),
    USERS_DELAY("users?delay=3"),
    UNKNOWN_23("unknown/23"),
    USERS_23("users/23"),

    // POST requests
    USERS("users"),
    REGISTER("register"),
    LOGIN("login"),

    // PUT/PATCH/DELETE requests
    USERS_2("users/2");

    private final String path;

    ApiPaths(String path) {

        this.path = path;
    }

}
