package com.bbva.ccol;

import com.bbva.ccol.dto.employees.EmployeeDTO;
import com.bbva.ccol.dto.employees.pagination.PaginationIn;
import com.bbva.ccol.dto.employees.response.PaginationOut;
import com.bbva.elara.transaction.AbstractTransaction;
import java.util.List;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractCCOLT00501COTransaction extends AbstractTransaction {

	public AbstractCCOLT00501COTransaction(){
	}


	/**
	 * Return value for input parameter paginationIn
	 */
	protected PaginationIn getPaginationin(){
		return (PaginationIn)this.getParameter("paginationIn");
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
