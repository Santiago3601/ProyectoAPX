package com.bbva.ccol.dto.employee.utils;

public enum Validaciones {
    RFC_PER(12),
    RFC_EMP(13),
   ;

    private int params;

    public int getValues() {
        return params;
    }
    Validaciones (int params) {
        this.params = params;

    }
}
