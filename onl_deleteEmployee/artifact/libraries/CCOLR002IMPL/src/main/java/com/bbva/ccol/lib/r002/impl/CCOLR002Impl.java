package com.bbva.ccol.lib.r002.impl;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;

import com.bbva.ccol.lib.r002.impl.utils.DefVals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The CCOLR002Impl class...
 */
public class CCOLR002Impl extends CCOLR002Abstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCOLR002Impl.class);

	@Override
	public int executeDeleteEmployee(Long employeeID) {

		Map<String, Object> mapa = new HashMap<>();
		mapa.put("employee_number",employeeID);
		mapa.put("employee_status", DefVals.disableValueStatus());
		mapa.put("employee_registration_date", DefVals.getInvocationDate());

		int dato;
		try {
			dato = this.jdbcUtils.update("sql.update.deleteEmployee", mapa);
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
			this.addAdviceWithDescription("CCOL0000003","error time out");
			LOGGER.info(t.getMessage());
		}
		LOGGER.info("response Impl: {}", dato);
		return dato;
	}

	/**
	 * The execute method...
	 */

}
