package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
import com.bbva.ccol.dto.employee.response.PaginationOut;
import com.bbva.elara.transaction.AbstractTransaction;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractCCOLT00401COTransaction extends AbstractTransaction {

	public AbstractCCOLT00401COTransaction(){
	}


	/**
	 * Return value for input parameter paginationIn
	 */
	protected PaginationIn getPaginationin(){
		return (PaginationIn)this.getParameter("paginationIn");
	}

	/**
	 * Return value for input parameter EmployeeDTO
	 */
	protected EmployeeDTO getEmployeedto(){
		return (EmployeeDTO)this.getParameter("employeeDTO");
	}

	/**
	 * Set value for List<EmployeeDTO> output parameter employeeDTOList
	 */
	protected void setEmployeedtolist(final List<EmployeeDTO> field){
		this.addParameter("employeeDTOList", field);
	}

	/**
	 * Set value for PaginationOut output parameter paginationOut
	 */
	protected void setPaginationout(final PaginationOut field){
		this.addParameter("paginationOut", field);
	}
}
