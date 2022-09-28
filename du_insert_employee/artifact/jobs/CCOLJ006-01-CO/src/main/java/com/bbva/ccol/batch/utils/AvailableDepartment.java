package com.bbva.ccol.batch.utils;

public enum AvailableDepartment {
    RRHH("RRHH"),
    SISTEMAS("SISTEMAS"),
    FINANZAS("FINANZAS");

    private String deps;

    public String getValues() {
        return deps;
    }

    AvailableDepartment(String deps) {
        this.deps = deps;
    }
}