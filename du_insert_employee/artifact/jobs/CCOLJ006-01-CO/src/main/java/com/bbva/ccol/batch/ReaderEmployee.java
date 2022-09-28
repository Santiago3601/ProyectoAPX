package com.bbva.ccol.batch;

import com.bbva.ccol.batch.utils.AvailableDepartment;
import com.bbva.ccol.batch.utils.DefValues;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReaderEmployee implements FieldSetMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderEmployee.class);

    @Override
    public Object mapFieldSet(FieldSet fieldSet) throws BindException {
        String register = fieldSet.readString("employeeId");

        LOGGER.info("Registro: {}", register);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        String[] datos = StringUtils.splitPreserveAllTokens(register, ",", 9);
//        String[] datos = register.split(",");
        if (datos[0].trim().isEmpty()) {
            datos[0] = DefValues.NAME.getValues();
        }
        employeeDTO.setEmployee_name(datos[0]);
        employeeDTO.setEmployee_department(datos[1]);
        employeeDTO.setEmployee_rfc(cleanSpaces(datos[2]));
        employeeDTO.setEmployee_email(cleanSpaces(datos[3]));
        employeeDTO.setEmployee_phone(cleanSpaces(datos[4]));
        employeeDTO.setEmployee_address(datos[5]);


        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:SSS");
        LocalDate fecha = LocalDate.parse(datos[6], formato);

        employeeDTO.setEmployee_registration_date(Date.from(fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//        employeeDTO.setEmployee_registration_date(new Date());
        if (datos[7].trim().isEmpty()) {
            datos[7] = DefValues.SALARY.getValues();
        }
        employeeDTO.setEmployee_status(Long.parseLong(datos[7]));
        employeeDTO.setSalary(Long.parseLong(datos[7]));

        return employeeDTO;
    }

    private String cleanSpaces(String paramIn) {
        return paramIn.replace(" ", "");
    }
}
