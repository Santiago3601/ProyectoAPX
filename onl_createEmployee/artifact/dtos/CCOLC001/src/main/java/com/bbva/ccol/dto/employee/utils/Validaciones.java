package com.bbva.ccol.dto.employee.utils;

public enum Validaciones {
    RFC_PER(13),
    RFC_EMP(12),
   ;

    private int params;

    public int getValues() {
        return params;
    }
    Validaciones (int params) {
        this.params = params;

    }
}
