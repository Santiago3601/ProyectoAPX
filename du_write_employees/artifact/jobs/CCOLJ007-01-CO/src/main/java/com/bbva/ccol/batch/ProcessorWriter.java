package com.bbva.ccol.batch;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class ProcessorWriter implements ItemProcessor<List<EmployeeDTO>, List<EmployeeDTO>> {
    @Override
    public List<EmployeeDTO> process(List<EmployeeDTO> employeeDTOS) throws Exception {
        int cont = 0;
        List<EmployeeDTO> listaRetorno = new ArrayList<>();
        for (EmployeeDTO employeeDTO : employeeDTOS){

            if(employeeDTO.getEmployee_department().equals("RRHH")){
                listaRetorno.add(employeeDTO);
            }

        }
        return listaRetorno;
    }
}
