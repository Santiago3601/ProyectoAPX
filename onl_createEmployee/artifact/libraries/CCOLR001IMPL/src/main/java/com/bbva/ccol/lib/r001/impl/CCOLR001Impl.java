package com.bbva.ccol.lib.r001.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The CCOLR001Impl class...
 */
public class CCOLR001Impl extends CCOLR001Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCOLR001Impl.class);

	/**
	 * The execute method...
	 */
	@Override
	public int executeCreateEmployee(EmployeeDTO employeeDTO) {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("employee_name",employeeDTO.getEmployee_name());
		mapa.put("employee_department",employeeDTO.getEmployee_department());
		mapa.put("employee_rfc",employeeDTO.getEmployee_rfc());
		mapa.put("employee_email",employeeDTO.getEmployee_email());
		mapa.put("employee_phone",employeeDTO.getEmployee_phone());
		mapa.put("employee_address",employeeDTO.getEmployee_address());
		mapa.put("employee_registration_date",employeeDTO.getEmployee_registration_date());
		mapa.put("employee_status",employeeDTO.getEmployee_status());
		mapa.put("salary",employeeDTO.getSalary());
		int dato;
		try {
			dato = this.jdbcUtils.update("sql.insert.createEmployee", mapa);
		}catch (NoResultException e){
			dato = -1;
			this.addAdviceWithDescription("CCOL00000001","error en conexión");
			LOGGER.info(e.getMessage());
		}catch (BusinessException b){
			dato = -2;
			this.addAdviceWithDescription("CCOL00000002","error de negocio");
			LOGGER.info(b.getMessage());
		}catch (TimeoutException t){
			dato = -3;
			this.addAdviceWithDescription("CCOL00000003","error time out");
			LOGGER.info(t.getMessage());
		}
		LOGGER.info("response: {}", dato);
		return dato;

	}

}
