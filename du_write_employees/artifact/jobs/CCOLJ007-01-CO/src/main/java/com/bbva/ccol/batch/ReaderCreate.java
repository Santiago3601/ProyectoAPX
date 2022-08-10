package com.bbva.ccol.batch;



import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
import com.bbva.ccol.lib.r007.CCOLR007;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class ReaderCreate implements ItemReader<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderCreate.class);
    private CCOLR007 ccolR007;
    private Integer pageKey = 1;
    private Integer pageSize = 5;

    private String department;


    public CCOLR007 getCcolR007() {
        return ccolR007;
    }

    public void setCcolR007(CCOLR007 ccolR007) {
        this.ccolR007 = ccolR007;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        PaginationIn paginationIn = new PaginationIn();
        paginationIn.setPaginationKey(pageKey.toString());
        paginationIn.setPageSize(Long.parseLong(pageSize.toString()));

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_department(department);

        LOGGER.info("Paginacion {}", pageKey);

        if (pageKey >= 0) {
            List<Map<String, Object>> response = ccolR007.executeListEmployee(paginationIn, employeeDTO);

            if ( response.isEmpty() ) {
                pageKey = 0;
                LOGGER.info("Fin de ejecucion de JOB ");
            } else {
                pageKey += pageSize;
                List<String> listEmployee = recoridoMapas(response);

                return listEmployee;
            }


        } else {
            LOGGER.info("No existe informacion ");
        }

        return null;
    }

    public List<String> recoridoMapas(List<Map<String, Object>> map) {
        List<String> listEmployee = new ArrayList<>();

        for (Map<String, Object> dto: map) {

            listEmployee.add(dto.get("employee_number").toString());
            listEmployee.add(dto.get("employee_name").toString());
            listEmployee.add(dto.get("employee_department").toString());
            listEmployee.add(dto.get("employee_rfc").toString());
            listEmployee.add(dto.get("employee_email").toString());
            listEmployee.add(dto.get("employee_phone").toString());
            listEmployee.add(dto.get("employee_address").toString());
            listEmployee.add(dto.get("employee_registration_date").toString());
            listEmployee.add(dto.get("employee_status").toString());
            listEmployee.add(dto.get("salary").toString() + "\n");

        }

        return listEmployee;
    }

}
