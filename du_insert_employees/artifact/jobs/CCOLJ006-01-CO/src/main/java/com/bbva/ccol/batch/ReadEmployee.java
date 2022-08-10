package com.bbva.ccol.batch;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReadEmployee implements FieldSetMapper {


    @Override
    public Object mapFieldSet(FieldSet fieldSet) throws BindException {
        String register = fieldSet.readString("employeeId");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String[] datos = register.split(",");
        employeeDTO.setEmployee_name(datos[1]);
        employeeDTO.setEmployee_department(datos[2]);
        employeeDTO.setEmployee_rfc(datos[3]);
        employeeDTO.setEmployee_email(datos[4]);
        employeeDTO.setEmployee_phone(datos[5]);
        employeeDTO.setEmployee_address(datos[6]);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");
        LocalDate fecha = LocalDate.parse(datos[7], formato);

        employeeDTO.setEmployee_registration_date(Date.from(fecha.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        employeeDTO.setEmployee_status(Long.parseLong(datos[8]));
        employeeDTO.setSalary(Long.parseLong(datos[9]));
        return employeeDTO;
    }
}
