package com.bbva.ccol.batch.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import org.mockito.Mockito;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.osgi.mock.MockServiceReference;

public class MockBundleContext implements BundleContext,
		ApplicationContextAware {

	private List<String> stringList;

	private ApplicationContext applicationContext;

	public MockBundleContext(final List<String> stringList) {
		this.stringList = stringList;
	}

	@Override
	public String getProperty(String key) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Bundle getBundle() {
		final Bundle mockBundle = Mockito.mock(Bundle.class);
		return mockBundle;
	}

	@Override
	public Bundle installBundle(String location, InputStream input)
			throws BundleException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Bundle installBundle(String location) throws BundleException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Bundle getBundle(long id) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Bundle[] getBundles() {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void addServiceListener(ServiceListener listener, String filter)
			throws InvalidSyntaxException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void addServiceListener(ServiceListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void removeServiceListener(ServiceListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void addBundleListener(BundleListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void removeBundleListener(BundleListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void addFrameworkListener(FrameworkListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void removeFrameworkListener(FrameworkListener listener) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public ServiceRegistration<?> registerService(String[] clazzes,
			Object service, Dictionary<String, ?> properties) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public ServiceRegistration<?> registerService(String clazz, Object service,
			Dictionary<String, ?> properties) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public <S> ServiceRegistration<S> registerService(Class<S> clazz,
			S service, Dictionary<String, ?> properties) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public ServiceReference<?>[] getServiceReferences(String clazz,
			String filter) throws InvalidSyntaxException {	
		ServiceReference<?>[] serviceReferences = new ServiceReference<?>[1];
		serviceReferences[0] = getServiceReference(clazz);
		return serviceReferences;
	}

	@Override
	public ServiceReference<?>[] getAllServiceReferences(String clazz,
			String filter) throws InvalidSyntaxException {
		ServiceReference<?>[] serviceReferences = new ServiceReference<?>[1];
		serviceReferences[0] = getServiceReference(clazz);
		return serviceReferences;
	}

	@Override
	public ServiceReference<?> getServiceReference(String clazz) {
		return new MockServiceReference(new String[] { clazz });
	}

	@SuppressWarnings("unchecked")
	@Override
	public <S> ServiceReference<S> getServiceReference(Class<S> clazz) {
		return new MockServiceReference(new String[] { clazz.getName() });
	}

	@Override
	public <S> Collection<ServiceReference<S>> getServiceReferences(
			Class<S> clazz, String filter) throws InvalidSyntaxException {
		Collection<ServiceReference<S>> collection = new ArrayList<ServiceReference<S>>();
		collection.add(getServiceReference(clazz));
		return collection;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S> S getService(ServiceReference<S> reference) {
		return (S) serviceFactory(reference);
	}

	@Override
	public boolean ungetService(ServiceReference<?> reference) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public File getDataFile(String filename) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Filter createFilter(String filter) throws InvalidSyntaxException {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public Bundle getBundle(String location) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings("rawtypes")
	private Object serviceFactory(ServiceReference reference) {
		Object obService = null;
		String[] objectClass = (String[]) reference
				.getProperty(Constants.OBJECTCLASS);
		Class<?> clasz;
		try {
			clasz = Class.forName(objectClass[0]);
			@SuppressWarnings("unchecked")
			Map<String, Class> type = (Map<String, Class>) applicationContext
					.getBeansOfType(clasz);
			if (type.size() == 1) {
				for (String set : type.keySet()) {
					obService = applicationContext.getBean(set);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obService;
	}
	
	@Override
	public <S> ServiceObjects<S> getServiceObjects(ServiceReference<S> serviceReference) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public <S> ServiceRegistration<S> registerService(Class<S> registerClass, ServiceFactory<S> serviceFactory,
			Dictionary<String, ?> dictionary) {
		throw new UnsupportedOperationException("Operation not supported");
	}

}
