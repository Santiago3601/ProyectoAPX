package com.bbva.ccol.batch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.osgi.framework.BundleContext;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.configuration.manager.application.ApxBackend;
import com.bbva.elara.configuration.manager.application.BackendConfiguration;
import com.bbva.elara.configuration.manager.application.factory.ApplicationConfigurationServiceFactory;

public class ConfigurationFactoryMock implements
		ApplicationConfigurationServiceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bbva.elara.configuration.manager.application.factory.
	 * ApplicationConfigurationServiceFactory
	 * #getApplicationConfigurationService(org.osgi.framework.BundleContext)
	 */
	@Override
	public ApplicationConfigurationService getApplicationConfigurationService(
			BundleContext arg0) {
		final Properties properties = new Properties();

		InputStream is = this.getClass().getResourceAsStream(
				"/META-INF/spring/configuration-application-test.properties");

		try {
			properties.load(is);
		} catch (IOException e) {
		}

		ApplicationConfigurationService connectorConfigBundle = new ApplicationConfigurationService() {

			@Override
			public String getProperty(String property) {				
				return properties.getProperty(property);
			}

			@Override
			public String getDefaultProperty(String property,
					String defaultProperty) {
				return properties.getProperty(property, defaultProperty);
			}
			
			@Override
			public Properties getAllProperties() {				
				return properties;
			}
			
			@Override
			public BackendConfiguration getBackendConfiguration(ApxBackend arg0, String arg1) {
				return null;
			}			
		};
		return connectorConfigBundle;
	}
}
