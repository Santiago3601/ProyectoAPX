package com.bbva.ccol.lib.r005;

import com.bbva.ccol.dto.employees.EmployeeDTO;
import com.bbva.ccol.dto.employees.pagination.PaginationIn;

import java.util.List;

/**
 * The  interface CCOLR005 class...
 */
public interface CCOLR005 {

	/**
	 * The execute method...
	 */
	List<EmployeeDTO> executeListCustomer(PaginationIn paginationIn);
	Integer executeListEmployeePaginacion();

}
