package com.bbva.ccol.lib.r001;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/CCOLR001-app.xml",
		"classpath:/META-INF/spring/CCOLR001-app-test.xml",
		"classpath:/META-INF/spring/CCOLR001-arc.xml",
		"classpath:/META-INF/spring/CCOLR001-arc-test.xml" })
public class CCOLR001Test {

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
		//ccolR001.executeCreateCustomer();
//		Assert.assertEquals(0, context.getAdviceList().size());
	}

}
