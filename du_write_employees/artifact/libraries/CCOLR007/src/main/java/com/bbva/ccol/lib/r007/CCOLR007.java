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

	List<EmployeeDTO> executeListCustomer();

	Integer executeListEmployeePaginacion( );

	String executeDistinctDepartment();
	Map<Integer,String> executeDistinctDepartment_2();


}
