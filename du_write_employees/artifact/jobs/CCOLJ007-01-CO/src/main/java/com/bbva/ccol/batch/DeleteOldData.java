package com.bbva.ccol.batch;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.lib.r007.CCOLR007;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Calendar;

public class DeleteOldData implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteOldData.class);

    private CCOLR007 ccolR007;

    public void setCcolR007(CCOLR007 ccolR007) {
        this.ccolR007 = ccolR007;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        Calendar fecha = Calendar.getInstance();
        fecha.set((Calendar.YEAR - 1), Calendar.MONTH, Calendar.DAY_OF_MONTH);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_registration_date(fecha.getTime());

        String response = ccolR007.executeDeleteEmployee(employeeDTO);
        LOGGER.info(response);

        return RepeatStatus.FINISHED;
    }

}
