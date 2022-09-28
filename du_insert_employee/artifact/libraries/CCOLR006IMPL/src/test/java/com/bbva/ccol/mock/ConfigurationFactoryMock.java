package com.bbva.ccol.mock;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.configuration.manager.application.factory.ApplicationConfigurationServiceFactory;

import org.mockito.Mockito;
import org.osgi.framework.BundleContext;

public class ConfigurationFactoryMock implements ApplicationConfigurationServiceFactory {

	@Override
	public ApplicationConfigurationService getApplicationConfigurationService(BundleContext arg0) {
		return Mockito.mock(ApplicationConfigurationService.class);
	}
}
