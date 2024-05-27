package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum PetroleumOperationType implements EnumClass<String> {

    INBOUND("A"),
    OUTBOUND("B");

    private final String id;

    PetroleumOperationType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static PetroleumOperationType fromId(String id) {
        for (PetroleumOperationType at : PetroleumOperationType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}