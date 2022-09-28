package com.bbva.ccol.batch.utils;

public enum DefValues {

    NAME("PEDRO"),
    SALARY("000000000000001"),
    ;

    private String vals;

    public String getValues() {
        return vals;
    }

    DefValues(String vals) {
        this.vals = vals;

    }
}