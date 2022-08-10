package com.bbva.ccol.batch;

import com.bbva.ccol.dto.employee.EmployeeDTO;

import com.bbva.ccol.lib.r006.CCOLR006;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WriterEmployee implements ItemWriter<EmployeeDTO> {

    private CCOLR006 ccolR006;

    private static final Logger LOGGER = LoggerFactory.getLogger(WriterEmployee.class);

    public void setCcolR006(CCOLR006 ccolR006) {
        this.ccolR006 = ccolR006;
    }


    @Override
    public void write(List<? extends EmployeeDTO> list) throws Exception {
        int count = 0;

        for (EmployeeDTO listaemployee : list) {
            int insert = ccolR006.executeCreateEmployee(listaemployee);
            count += insert;
        }
        LOGGER.info("Registros:  {}", count);
    }
}
