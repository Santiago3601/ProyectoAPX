package com.bbva.ccol.lib.r004;

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
		"classpath:/META-INF/spring/CCOLR004-app.xml",
		"classpath:/META-INF/spring/CCOLR004-app-test.xml",
		"classpath:/META-INF/spring/CCOLR004-arc.xml",
		"classpath:/META-INF/spring/CCOLR004-arc-test.xml" })
public class CCOLR004Test {

	@Spy
	private Context context;

	@Resource(name = "ccolR004")
	private CCOLR004 ccolR004;

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
		Object result = this.ccolR004;
		if(this.ccolR004 instanceof Advised){
			Advised advised = (Advised) this.ccolR004;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		//ccolR004.execute();
		//Assert.assertEquals(0, context.getAdviceList().size());
	}
	
}
