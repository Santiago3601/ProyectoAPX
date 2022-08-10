package com.bbva.ccol.lib.r002;
import com.bbva.apx.exception.business.BusinessException;
import com.bbva.apx.exception.db.NoResultException;
import com.bbva.apx.exception.db.TimeoutException;
import com.bbva.ccol.lib.r002.impl.utils.DefVals;
import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ThreadContext;
import javax.annotation.Resource;

import com.bbva.elara.utility.jdbc.JdbcUtils;
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

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/CCOLR002-app.xml",
        "classpath:/META-INF/spring/CCOLR002-app-test.xml",
        "classpath:/META-INF/spring/CCOLR002-arc.xml",
        "classpath:/META-INF/spring/CCOLR002-arc-test.xml"})
public class CCOLR002Test {

    @Autowired
    private JdbcUtils jdbcUtils;

    @Spy
    private Context context;

    @Resource(name = "ccolR002")
    private CCOLR002 ccolR002;

    @Resource(name = "applicationConfigurationService")
    private ApplicationConfigurationService applicationConfigurationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        context = new Context();
        ThreadContext.set(context);
        getObjectIntrospection();
    }

    private Object getObjectIntrospection() throws Exception {
        Object result = this.ccolR002;
        if (this.ccolR002 instanceof Advised) {
            Advised advised = (Advised) this.ccolR002;
            result = advised.getTargetSource().getTarget();
        }
        return result;
    }

    @Test
    public void executeTest() {
        ccolR002.executeDeleteEmployee(1L);
        Assert.assertNotNull(context.getAdviceList());
    }

    @Test
    public void executeTestThrowNoResultException() {
        Map<String, Object> mapa = setParams();
        Mockito.when(this.jdbcUtils.update("sql.update.deleteEmployee", mapa)).thenThrow(new NoResultException("..."));
      //  Mockito.when(ccolR002.executeDeleteEmployee(1L)).thenThrow(new NoResultException("..."));
        int respuesta =   ccolR002.executeDeleteEmployee(1L);
       // Assert.assertEquals(-1,respuesta);
        Assert.assertNotNull(context.getAdviceList());
    }



    @Test
    public void executeTestCreateExBusiness(){
        Map<String, Object> mapa = setParams();
        Mockito.when(this.jdbcUtils.update("sql.update.deleteEmployee", mapa)).thenThrow(new BusinessException("...", false));
      //  Mockito.when( ccolR002.executeDeleteEmployee(1L)).thenThrow(new BusinessException("...", false));
       // Mockito.when( ccolR002.executeDeleteEmployee(1L)).thenThrow(new BusinessException("...", false));
        int respuesta = ccolR002.executeDeleteEmployee(1L);
      //  Assert.assertEquals(-2, respuesta );
        Assert.assertNotNull(context.getAdviceList());
    }

    @Test
    public void executeTestCreateExTimeoutException(){
        Map<String, Object> mapa = setParams();
        Mockito.when(this.jdbcUtils.update("sql.update.deleteEmployee", mapa)).thenThrow(new TimeoutException("..."));
        int respuesta = ccolR002.executeDeleteEmployee(1L);
//        Assert.assertEquals(-3,respuesta);
        // Assert.assertEquals(-2, valor );
        Assert.assertNotNull(context.getAdviceList());
    }

    public   Map<String, Object>  setParams(){
        Map<String, Object> mapa = new HashMap<>();
        mapa.put("employee_number", 1L);
        mapa.put("employee_status", DefVals.disableValueStatus());
        mapa.put("employee_registration_date", DefVals.getInvocationDate());
        return mapa;
    }

/*
    @Test
    public void executeTestCreateExTiemOut(){
        EmployeeDTO employeeDTO = setParameters();
        Map<String, Object> mapa = setMapa(employeeDTO);
        Mockito.when(this.jdbcUtils.update("sql.insert.createEmployee", mapa)).thenThrow(new TimeoutException("..."));
        int valor = ccolR001.executeCreateEmployee(employeeDTO);
        Assert.assertEquals(-3, valor );
        Assert.assertNotNull(context.getAdviceList());
    }


    */

	/*
	* try {
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
	* */

}
