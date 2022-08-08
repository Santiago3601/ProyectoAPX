package com.bbva.ccol.dto.employee.utils;

public enum Validaciones {
    RFC_PER(13),
    RFC_EMP(12),
    CEL_CON_LADA(12),
    CEL_SIN_LADA(10),
    LADA_DEF(55)

    ;

    private int params;

    public int getValues() {
        return params;
    }
    Validaciones (int params) {
        this.params = params;

    }
}
