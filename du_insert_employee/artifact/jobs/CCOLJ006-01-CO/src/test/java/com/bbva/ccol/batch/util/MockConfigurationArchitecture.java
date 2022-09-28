package com.bbva.ccol.batch.util;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.configuration.Configuration;

import com.bbva.elara.configuration.manager.ConfigurationManager;


public class MockConfigurationArchitecture implements ConfigurationManager {

	private Properties properties;
	
	public MockConfigurationArchitecture() throws IOException {
		properties = new Properties();
		this.properties.load(this.getClass().getResourceAsStream("/META-INF/spring/qwpj-configurationArchitecture.properties"));
	}
	
	@Override
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
			
	@Override
	public String getProperty(final String key,final String defaultValue) {		
		return this.properties.getProperty(key, defaultValue);
	}
	
	@Override
	public Configuration getSubset(String arg0) {
		return null;
	}

}

