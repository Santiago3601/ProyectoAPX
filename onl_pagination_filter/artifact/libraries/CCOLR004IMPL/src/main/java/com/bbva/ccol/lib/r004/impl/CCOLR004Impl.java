package com.bbva.ccol.lib.r004.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The CCOLR004Impl class...
 */
public class CCOLR004Impl extends CCOLR004Abstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCOLR004Impl.class);

    /**
     * The execute method...
     */
    @Override
    public List<EmployeeDTO> executeListCustomer(PaginationIn paginationIn, EmployeeDTO employee) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("employee_name",employee.getEmployee_name());

        List<Map<String, Object>> dato = null;
        Map<String, Object> manejoErrores = new HashMap<>();
        try {
            dato = this.jdbcUtils.pagingQueryForList(
                    "sql.find.listarempleados",
                    Integer.parseInt(paginationIn.getPaginationKey()),
                    Integer.parseInt(paginationIn.getPageSize().toString()),
                    filter
                    );
        } catch (NoResultException e) {
            manejoErrores.put("NoResultException", -1);
            dato.add(manejoErrores);
            this.addAdviceWithDescription("CCOL00000001", "error en conexión");
            LOGGER.info(e.getMessage());
        } catch (BusinessException b) {
            manejoErrores.put("BusinessException", -2);
            dato.add(manejoErrores);
            this.addAdviceWithDescription("CCOL00000002", "error de negocio");
            LOGGER.info(b.getMessage());
        } catch (TimeoutException t) {
            manejoErrores.put("TimeoutException", -3);
            dato.add(manejoErrores);
            this.addAdviceWithDescription("CCOL00000003", "error time out");
            LOGGER.info(t.getMessage());
        }
        LOGGER.info("response Impl: {}", dato);

        List<EmployeeDTO> lista = new ArrayList<>();
        if (dato != null) {
            for (Map<String, Object> registro : dato
            ) {
                LOGGER.info("DATO : {}", registro.toString());
                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.setEmployee_number(Long.parseLong(registro.get("employee_number").toString()));
                employeeDTO.setEmployee_name(registro.get("employee_name").toString());
                employeeDTO.setEmployee_department(registro.get("employee_department").toString());
                employeeDTO.setEmployee_rfc(registro.get("employee_rfc").toString());
                employeeDTO.setEmployee_email(registro.get("employee_email").toString());
                employeeDTO.setEmployee_phone(registro.get("employee_phone").toString());
                employeeDTO.setEmployee_address(registro.get("employee_address").toString());
                String fecha = registro.get("employee_registration_date").toString();
                SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
                Date date = new Date();
                try {
                    LOGGER.info("ENTRO FECHA");
                    date = d.parse(fecha);
                }catch (ParseException p){
                    LOGGER.info("EXCEPCION FECHA");
                    this.addAdviceWithDescription("CCOL00000005", "error en formato fecha");
                }

                employeeDTO.setEmployee_registration_date(date);
                employeeDTO.setEmployee_status(Long.parseLong(registro.get("employee_status").toString()));
                employeeDTO.setSalary(Long.parseLong(registro.get("salary").toString()));
                LOGGER.info("CUSTOMER DTO : {}", employeeDTO);
                lista.add(employeeDTO);
            }
        } else {
            this.addAdviceWithDescription("CCOL00000004", "Respuesta nula");
        }
        return lista;
    }

    @Override
    public Integer executeListEmployeePaginacion(EmployeeDTO employeeDTO) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("employee_name",employeeDTO.getEmployee_name());
        String dato = "";
        //Map<String, Object> total = new HashMap<>();
        try {
            dato = this.jdbcUtils.queryForString("sql.find.totalempleados",filter);
        } catch (NoResultException e) {
            dato = "-1";
            this.addAdviceWithDescription("CCOL00000001", "error en conexión");
            LOGGER.info(e.getMessage());
        } catch (BusinessException b) {
            dato = "-2";
            this.addAdviceWithDescription("CCOL00000002", "error de negocio");
            LOGGER.info(b.getMessage());
        } catch (TimeoutException t) {
            dato = "-3";
            this.addAdviceWithDescription("CCOL00000003", "error time out");
            LOGGER.info(t.getMessage());
        }
        LOGGER.info("TOTAL EMPLEADOS : {}", dato);
        return Integer.valueOf(dato);
    }
}
