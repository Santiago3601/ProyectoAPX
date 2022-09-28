package com.bbva.ccol.mock;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.lib.r006.CCOLR006;

public class CCOLR006Mock implements CCOLR006 {
    @Override
    public int executeCreateEmployee(EmployeeDTO employeeDTO) {
        return 1;
    }

    @Override
    public int executeDeleteCheckData() {
        return 1;
    }

    @Override
    public int executeDeleteOldData() {
        return 1;
    }
}
