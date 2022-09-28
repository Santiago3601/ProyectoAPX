package com.bbva.ccol.lib.r007.impl.utils;

public enum BaseParameters {

    ERROR_ADVICE_CONEXION("CCOL00000001"),
    ERROR_ADVICE_TIME("CCOL00000003"),

    ERROR_ADVICE_NULL("CCOL00000004"),
    ERROR_ADVICE_BUSINESS("CCOL00000002");

    private String values;

    public String getValues() {
        return values;
    }

    BaseParameters(String values) {
        this.values = values;
    }
}
