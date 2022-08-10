package com.bbva.ccol.lib.r007.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
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
	public int executeCreateEmployee(EmployeeDTO employeeDTO) {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("employee_number",employeeDTO.getEmployee_number());
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
			dato = this.jdbcUtils.update("sql.insert.crearcliente", mapa);
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

	@Override
	public int executeUpdateEmployee(EmployeeDTO employeeDTO) {
		LOGGER.info("customerDto Impl: {}", employeeDTO.toString());
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("employee_number",employeeDTO.getEmployee_number());
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
			dato = this.jdbcUtils.update("sql.update.actualizacliente", mapa);
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
		LOGGER.info("response Impl: {}", dato);
		return dato;
	}

	@Override
	public String executeDeleteEmployee(EmployeeDTO employeeDTO) {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("customerId",employeeDTO.getEmployee_number());
		int dato;
		try {
			dato = this.jdbcUtils.update("sql.delete.deleteOldData ", mapa);
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
		LOGGER.info("response Impl: {}", dato);
		return String.valueOf(dato);
	}

	@Override
	public EmployeeDTO executeGetEmployeeById(Long employeeId) {
		return null;
	}

	@Override
	public List<Map<String, Object>> executeListEmployee(PaginationIn paginationIn, EmployeeDTO employeeDTO2) {
		List<Map<String, Object>> dato = null;
		Map<String, Object> manejoErrores = new HashMap<>();

		try {
			manejoErrores.put("city", employeeDTO2.getEmployee_department());
			dato = this.jdbcUtils.pagingQueryForList("sql.find.listarclientes", Integer.parseInt( paginationIn.getPaginationKey()),  Integer.parseInt(paginationIn.getPageSize().toString()));
		}catch (NoResultException e){
			manejoErrores.put("NoResultException", -1);
			dato.add(manejoErrores);
			this.addAdviceWithDescription("CCOL00000001","error en conexión");
			LOGGER.info(e.getMessage());
		}catch (BusinessException b){
			manejoErrores.put("BusinessException", -2);
			dato.add(manejoErrores);
			this.addAdviceWithDescription("CCOL00000002","error de negocio");
			LOGGER.info(b.getMessage());
		}catch (TimeoutException t){
			manejoErrores.put("TimeoutException", -3);
			dato.add(manejoErrores);
			this.addAdviceWithDescription("CCOL00000003","error time out");
			LOGGER.info(t.getMessage());
		}
		LOGGER.info("response Impl: {}", dato);


		return dato;
	}

/*
	@Override
	public Integer executeListEmployeePaginacion() {
//		Integer dato = 0;
		String dato = "";
		Map<String, Object> total = new HashMap<>();
		try {
			dato = this.jdbcUtils.queryForString("sql.find.totalclientes", total);
		}catch (NoResultException e){
			dato = "-1";
			this.addAdviceWithDescription("CCOL00000001","error en conexión");
			LOGGER.info(e.getMessage());
		}catch (BusinessException b){
			dato = "-2";
			this.addAdviceWithDescription("CCOL00000002","error de negocio");
			LOGGER.info(b.getMessage());
		}catch (TimeoutException t){
			dato = "-3";
			this.addAdviceWithDescription("CCOL00000003","error time out");
			LOGGER.info(t.getMessage());
		}
		LOGGER.info("TOTAL CLIENTES : {}", dato);
		return Integer.valueOf(dato);
	}
* */

}
