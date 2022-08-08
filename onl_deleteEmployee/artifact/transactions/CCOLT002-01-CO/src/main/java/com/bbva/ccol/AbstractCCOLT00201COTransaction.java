package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractCCOLT00201COTransaction extends AbstractTransaction {

	public AbstractCCOLT00201COTransaction(){
	}


	/**
	 * Return value for input parameter employeeDTO
	 */
	protected EmployeeDTO getEmployeedto(){
		return (EmployeeDTO)this.getParameter("employeeDTO");
	}

	/**
	 * Set value for String output parameter estado
	 */
	protected void setEstado(final String field){
		this.addParameter("estado", field);
	}
}
