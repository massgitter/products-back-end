package com.openfabric.openfabricbackend.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum RoleEnum {
    ADMIN("admin");
    private final String code;
    RoleEnum(String code){this.code=code;}

    public static class Roles{
        public static final String ADMIN = "ADMIN";
    }

    public String getCode(){return code;}
}
