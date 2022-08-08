package com.bbva.ccol.lib.r001;

import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.bbva.elara.utility.jdbc.JdbcUtils;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/CCOLR001-app.xml",
		"classpath:/META-INF/spring/CCOLR001-app-test.xml",
		"classpath:/META-INF/spring/CCOLR001-arc.xml",
		"classpath:/META-INF/spring/CCOLR001-arc-test.xml" })
public class CCOLR001Test {

	@Autowired
	private JdbcUtils jdbcUtils;
	@Spy
	private Context context;

	@Resource(name = "ccolR001")
	private CCOLR001 ccolR001;

	@Resource(name = "applicationConfigurationService")
	private ApplicationConfigurationService applicationConfigurationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		context = new Context();
		ThreadContext.set(context);
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.ccolR001;
		if(this.ccolR001 instanceof Advised){
			Advised advised = (Advised) this.ccolR001;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}

	@Test
	public void executeTest(){
		ccolR001.executeCreateEmployee(setParameters());
		Assert.assertEquals(0, context.getAdviceList().size());
	}

	@Test
	public void executeTestCreateExNoResult(){

		EmployeeDTO employeeDTO = setParameters();
		Map<String, Object> mapa = setMapa(employeeDTO);
		Mockito.when(this.jdbcUtils.update("sql.insert.createEmployee", mapa)).thenThrow(new NoResultException("..."));
		int valor = ccolR001.executeCreateEmployee(employeeDTO);
		Assert.assertEquals(-1, valor );
		Assert.assertNotNull(context.getAdviceList());

	}

	@Test
	public void executeTestCreateExBusiness(){
		EmployeeDTO employeeDTO = setParameters();
		Map<String, Object> mapa = setMapa(employeeDTO);
		Mockito.when(this.jdbcUtils.update("sql.insert.createEmployee", mapa)).thenThrow(new BusinessException("...", false));
		int valor = ccolR001.executeCreateEmployee(employeeDTO);
		Assert.assertEquals(-2, valor );
		Assert.assertNotNull(context.getAdviceList());
	}

	@Test
	public void executeTestCreateExTiemOut(){
		EmployeeDTO employeeDTO = setParameters();
		Map<String, Object> mapa = setMapa(employeeDTO);
		Mockito.when(this.jdbcUtils.update("sql.insert.createEmployee", mapa)).thenThrow(new TimeoutException("..."));
		int valor = ccolR001.executeCreateEmployee(employeeDTO);
		Assert.assertEquals(-3, valor );
		Assert.assertNotNull(context.getAdviceList());
	}


	private Map<String, Object> setMapa(EmployeeDTO employeeDTO){
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("employee_number",employeeDTO.getEmployee_number());
		mapa.put("employee_name",employeeDTO.getEmployee_name());
		mapa.put("employee_department",employeeDTO.getEmployee_department());
		mapa.put("employee_rfc",employeeDTO.getEmployee_rfc());
		mapa.put("employee_email",employeeDTO.getEmployee_email());
		mapa.put("employee_phone",employeeDTO.getEmployee_phone());
		mapa.put("employee_address",employeeDTO.getEmployee_address());
		mapa.put("employee_registration_date",employeeDTO.getEmployee_registration_date());
		mapa.put("employee_status",employeeDTO.getEmployee_status());
		mapa.put("salary",employeeDTO.getSalary());
		return mapa;
	}
	private EmployeeDTO setParameters() {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployee_number(1L);
		employeeDTO.setEmployee_name("Santiago");
		employeeDTO.setEmployee_department("RRHH");
		employeeDTO.setEmployee_rfc("LUMA470929F37");
		employeeDTO.setEmployee_email("ejemplo@gmail.com");
		employeeDTO.setEmployee_phone("571234567890");
		employeeDTO.setEmployee_address("calle de la amargura");
		employeeDTO.setEmployee_registration_date(new Date());
		employeeDTO.setEmployee_status(1L);
		employeeDTO.setSalary(80000L);
		return employeeDTO;
	}

}
