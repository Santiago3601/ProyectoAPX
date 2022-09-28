package com.bbva.ccol.batch;

import com.bbva.ccol.batch.utils.AvailableDepartment;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.lib.r006.CCOLR006;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WriterEmployee implements ItemWriter<EmployeeDTO> {
    private CCOLR006 ccolR006;

    public void setCcolR006(CCOLR006 ccolR006) {
        this.ccolR006 = ccolR006;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(WriterEmployee.class);

    @Override
    public void write(List<? extends EmployeeDTO> list) throws Exception {
        int count = 0;

        for (EmployeeDTO listaEmpleados : list) {
            boolean deptValid = false;
            for (AvailableDepartment dept : AvailableDepartment.values()) {
                if (listaEmpleados.getEmployee_department()
                        .toUpperCase()
                        .trim()
                        .contains(dept.getValues())) {
                    deptValid = true;
                    break;
                }

            }
            int insert = 0;
            if (deptValid) {
                insert = ccolR006.executeCreateEmployee(listaEmpleados);
            }
            count += insert;

            LOGGER.info("Registros:  {}", count);

        }
    }
}
