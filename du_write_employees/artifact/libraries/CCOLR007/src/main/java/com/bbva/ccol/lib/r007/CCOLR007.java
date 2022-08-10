package com.bbva.ccol.lib.r007;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;

import java.util.List;
import java.util.Map;

/**
 * The  interface CCOLR007 class...
 */
public interface CCOLR007 {

	/**
	 * The execute method...
	 */
	int executeCreateEmployee(EmployeeDTO employeeDTO);

	int executeUpdateEmployee(EmployeeDTO employeeDTO);

	String executeDeleteEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO executeGetEmployeeById(Long customerId);

	List<Map<String, Object>> executeListEmployee(PaginationIn paginationIn, EmployeeDTO employeeDTO);



}
