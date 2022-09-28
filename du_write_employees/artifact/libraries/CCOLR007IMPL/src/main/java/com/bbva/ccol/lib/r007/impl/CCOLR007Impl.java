package com.bbva.ccol.lib.r007.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
import com.bbva.ccol.lib.r007.impl.utils.BaseParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The CCOLR007Impl class...
 */
public class CCOLR007Impl extends CCOLR007Abstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCOLR007Impl.class);

    /**
     * The execute method...
     */


    @Override
    public List<EmployeeDTO> executeListCustomer() {
        List<Map<String, Object>> dato = null;
        Map<String, Object> manejoErrores = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("estatus", 1);
//		data.put("department", listaDep);
        LOGGER.info("MAPA SQL : {}", data.toString());
        try {
            dato = this.jdbcUtils.queryForList("sql.find.listaremployees",data);
        }catch (NoResultException e){
            manejoErrores.put("NoResultException", -1);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_CONEXION.getValues(),"error en conexión");
            LOGGER.info(e.getMessage());
        }catch (BusinessException b){
            manejoErrores.put("BusinessException", -2);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_BUSINESS.getValues(), "error de negocio");
            LOGGER.info(b.getMessage());
        }catch (TimeoutException t){
            manejoErrores.put("TimeoutException", -3);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_TIME.getValues(), "error time out");
            LOGGER.info(t.getMessage());
        }
        List<EmployeeDTO> lista = new ArrayList<>();
//		Si se encontro informaciòn y la lista de errores es 0
        if(dato != null && manejoErrores.size()==0){
            for (Map<String, Object> registro : dato) {
//				LOGGER.info("DATO : {}", registro.toString());
                EmployeeDTO employeeDto = new EmployeeDTO();
//				employeeDto.setNumber(Long.parseLong(registro.get("employee_number").toString()));
                employeeDto.setEmployee_name(registro.get("employee_name").toString());
                employeeDto.setEmployee_department(registro.get("employee_department").toString());
                employeeDto.setEmployee_rfc(registro.get("employee_rfc").toString());
                employeeDto.setEmployee_email(registro.get("employee_email").toString());
                employeeDto.setEmployee_phone(registro.get("employee_phone").toString());
                employeeDto.setEmployee_address(registro.get("employee_address").toString());
                employeeDto.setSalary(Long.parseLong(registro.get("salary").toString()));
                employeeDto.setEmployee_status(Long.parseLong(registro.get("employee_status").toString()));
                String fecha = registro.get("employee_registration_date").toString();
                SimpleDateFormat d = new SimpleDateFormat("dd-MM-yy");
                Date date = new Date();
                try {
//					LOGGER.info("ENTRO FECHA");
                    date = d.parse(fecha);
                }catch (ParseException p){
                    LOGGER.info("EXCEPCION FECHA");
                    this.addAdviceWithDescription("CAPX00000005", "error en formato fecha");
                }
                employeeDto.setEmployee_registration_date(date);
//				LOGGER.info("EMPLOYEEDTO : {}", employeeDTO);
                lista.add(employeeDto);
            }
        }else{
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_NULL.getValues(), "Respuesta nula");
        }

        return lista;
    }

    @Override
    public Integer executeListEmployeePaginacion() {
        String dato = "";
        Map<String, Object> total = new HashMap<>();
        total.put("estatus", 1);
//		total.put("department", listaDep);
        LOGGER.info("MAPA SQL : {}", total.toString());
        try {
            dato = this.jdbcUtils.queryForString("sql.find.totalemployees", total);
        }catch (NoResultException e){
            dato = "-1";
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_CONEXION.getValues(),"error en conexión");
            LOGGER.info(e.getMessage());
        }catch (BusinessException b){
            dato = "-2";
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_BUSINESS.getValues(), "error de negocio");
            LOGGER.info(b.getMessage());
        }catch (TimeoutException t){
            dato = "-3";
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_TIME.getValues(), "error time out");
            LOGGER.info(t.getMessage());
        }
        LOGGER.info("TOTAL EMPLOYEES : {}", dato);
        return Integer.valueOf(dato);
    }

    @Override
    public String executeDistinctDepartment() {
        List<Map<String, Object>> dato = null;
        Map<String, Object> manejoErrores = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("estatus", 1);
        try {
            dato = this.jdbcUtils.queryForList("sql.find.listardistinctdepartment", data);
        } catch (NoResultException e) {
            manejoErrores.put("NoResultException", -1);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_CONEXION.getValues(), "error en conexión");
            LOGGER.info(e.getMessage());
        } catch (BusinessException b) {
            manejoErrores.put("BusinessException", -2);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_BUSINESS.getValues(), "error de negocio");
            LOGGER.info(b.getMessage());
        } catch (TimeoutException t) {
            manejoErrores.put("TimeoutException", -3);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_TIME.getValues(), "error time out");
            LOGGER.info(t.getMessage());
        }
        LOGGER.info("response Impl: {}", dato);
        String lista = "";
        LOGGER.info("MANEJO ERRORES SIZE : {}", manejoErrores.toString());
        if (dato != null && manejoErrores.size() == 0) {
            for (Map<String, Object> registro : dato) {
                LOGGER.info("DATO : {}", registro.toString());
                String department = registro.get("(UPPER(EMPLOYEE_DEPARTMENT))").toString();
                LOGGER.info("DEPARTMENT : {}", department);
                lista = lista + "'"+ department+"'" + ",";
            }
        } else {
            this.addAdviceWithDescription("CCOL00000004", "Respuesta nula");
        }
        if(!lista.isEmpty())
            lista = lista.replaceFirst(".$","");
        return lista;
    }

    @Override
    public Map<Integer,String> executeDistinctDepartment_2() {
        List<Map<String, Object>> dato = null;
        Map<String, Object> manejoErrores = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("estatus", 1);
        try {
            dato = this.jdbcUtils.queryForList("sql.find.listardistinctdepartment", data);
        } catch (NoResultException e) {
            manejoErrores.put("NoResultException", -1);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_CONEXION.getValues(), "error en conexión");
            LOGGER.info(e.getMessage());
        } catch (BusinessException b) {
            manejoErrores.put("BusinessException", -2);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_BUSINESS.getValues(), "error de negocio");
            LOGGER.info(b.getMessage());
        } catch (TimeoutException t) {
            manejoErrores.put("TimeoutException", -3);
            dato.add(manejoErrores);
            this.addAdviceWithDescription(BaseParameters.ERROR_ADVICE_TIME.getValues(), "error time out");
            LOGGER.info(t.getMessage());
        }
        LOGGER.info("response Impl: {}", dato);

        LOGGER.info("MANEJO ERRORES SIZE : {}", manejoErrores.toString());
        Integer cont = 0;
        Map<Integer,String> response = new HashMap<>();
        if (dato != null && manejoErrores.size() == 0) {
            for (Map<String, Object> registro : dato) {

                cont++;
                response.put(cont,registro.get("(UPPER(EMPLOYEE_DEPARTMENT))").toString());
                LOGGER.info("DATO : {}", registro);
                LOGGER.info("ID : {}", cont);
                LOGGER.info("response : {}", response);
           /*     LOGGER.info("DATO : {}", registro.toString());
                String department = registro.get("(UPPER(EMPLOYEE_DEPARTMENT))").toString();
                LOGGER.info("DEPARTMENT : {}", department);
                lista = lista + "'"+ department+"'" + ",";*/
            }
//            if (response.size()==0){
//                response.put(0,"empty");
//            }
        } else {
            this.addAdviceWithDescription("CCOL00000004", "Respuesta nula");
        }
        return response;
    }
}
