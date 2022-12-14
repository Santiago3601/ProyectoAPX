package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.elara.transaction.AbstractTransaction;

/**
 * In this class, the input and output data is defined automatically through the setters and getters.
 */
public abstract class AbstractCCOLT00301COTransaction extends AbstractTransaction {

	public AbstractCCOLT00301COTransaction(){
	}


	/**
	 * Return value for input parameter employeeDTO
	 */
	protected EmployeeDTO getEmployeedto(){
		return (EmployeeDTO)this.getParameter("employeeDTO");
	}

	/**
	 * Set value for String output parameter mensaje
	 */
	protected void setMensaje(final String field){
		this.addParameter("mensaje", field);
	}
}
