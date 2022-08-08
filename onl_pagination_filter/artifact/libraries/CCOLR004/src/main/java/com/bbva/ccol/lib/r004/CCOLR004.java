package com.bbva.ccol.lib.r004;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;

import java.util.List;

/**
 * The  interface CCOLR004 class...
 */
public interface CCOLR004 {

	/**
	 * The execute method...
	 */
	List<EmployeeDTO> executeListCustomer(PaginationIn paginationIn, EmployeeDTO employee);
	Integer executeListEmployeePaginacion(EmployeeDTO employeeDTO);


}
