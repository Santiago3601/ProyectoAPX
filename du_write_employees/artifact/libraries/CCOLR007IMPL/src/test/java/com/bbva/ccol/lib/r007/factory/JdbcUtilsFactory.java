package com.bbva.ccol.lib.r007.factory;

import com.bbva.elara.utility.jdbc.JdbcUtils;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;

public class JdbcUtilsFactory implements com.bbva.elara.utility.jdbc.connector.factory.JdbcUtilsFactory {

	@Override
	public JdbcUtils getJdbcUtils(final BundleContext arg0) {
		return Mockito.mock(JdbcUtils.class);
	}
}
