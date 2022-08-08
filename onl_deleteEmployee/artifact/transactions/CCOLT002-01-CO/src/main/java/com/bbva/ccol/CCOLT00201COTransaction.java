package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.lib.r002.CCOLR002;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delete Method
 *
 */
public class CCOLT00201COTransaction extends AbstractCCOLT00201COTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCOLT00201COTransaction.class);

	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		CCOLR002 ccolR002 = this.getServiceLibrary(CCOLR002.class);
		EmployeeDTO employeeDTO = this.getEmployeedto();
		LOGGER.info("OBJETO CUSTOMER : {}", employeeDTO.toString());
		int response = ccolR002.executeDeleteEmployee(employeeDTO.getEmployee_number());
		LOGGER.info("respuesta: {}", (response));
		this.setEstado(String.valueOf(response));
		// TODO - Implementation of business logic
	}

}
