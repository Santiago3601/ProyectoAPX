package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.TransactionParameter;
import com.bbva.elara.domain.transaction.request.TransactionRequest;
import com.bbva.elara.domain.transaction.request.body.CommonRequestBody;
import com.bbva.elara.domain.transaction.request.header.CommonRequestHeader;
import com.bbva.elara.test.osgi.DummyBundleContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for transaction CCOLT00101COTransaction
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/META-INF/spring/elara-test.xml",
        "classpath:/META-INF/spring/CCOLT00101COTest.xml"})
public class CCOLT00101COTransactionTest {

    @Autowired
    private CCOLT00101COTransaction transaction;

    @Resource(name = "dummyBundleContext")
    private DummyBundleContext bundleContext;

    @Mock
    private CommonRequestHeader header;

    @Mock
    private TransactionRequest transactionRequest;

    @Before
    public void initializeClass() throws Exception {
        // Initializing mocks
        MockitoAnnotations.initMocks(this);
        // Start BundleContext
        this.transaction.start(bundleContext);
        // Setting Context
        this.transaction.setContext(new Context());
        // Set Body
        CommonRequestBody commonRequestBody = new CommonRequestBody();
        commonRequestBody.setTransactionParameters(new ArrayList<>());
        this.transactionRequest.setBody(commonRequestBody);
        // Set Header Mock
        this.transactionRequest.setHeader(header);
        // Set TransactionRequest
        this.transaction.getContext().setTransactionRequest(transactionRequest);
    }

    @Test
    public void testNotNull() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO = setParametersCorrect();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    @Test
    public void testFailSpecialCharacters() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO = setParametersFailSpecialCharacter();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    @Test
    public void testFailEmailStructure() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO = setParametersFailMailStructure();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    @Test
    public void testFailRFCEmp() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO = setParametersFailRFCEmp();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    @Test
    public void testFailRFCPerson_1() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO =  setParametersFailRFCPer_1();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    @Test
    public void testFailRFCPerson_2() {
        // Example to Mock the Header
        // Mockito.doReturn("ES").when(header).getHeaderParameter(RequestHeaderParamsName.COUNTRYCODE);
        EmployeeDTO employeeDTO =  setParametersFailRFCPer_2();
        addParameter("employeeDTO", employeeDTO);
        Assert.assertNotNull(this.transaction);
        this.transaction.execute();
    }

    //Métodos de seteo de parametros para los casos de prueba
    private EmployeeDTO setParametersCorrect() {
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

    private EmployeeDTO setParametersFailSpecialCharacter() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("LUMA470929F37@");
        employeeDTO.setEmployee_email("ejemplo@no_gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }

    private EmployeeDTO setParametersFailMailStructure() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("0UMA470929F37");
        employeeDTO.setEmployee_email("@gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }

    private EmployeeDTO setParametersFailRFCEmp() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("AUMA470G29F37");
        employeeDTO.setEmployee_email("@gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }

    private EmployeeDTO setParametersFailRFCPer_1() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("0UM470029F37");
        employeeDTO.setEmployee_email("@gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }

    private EmployeeDTO setParametersFailRFCPer_2() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("AUM470L29F37");
        employeeDTO.setEmployee_email("@gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }

    private EmployeeDTO setParametersTestIfRFCPer_() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployee_number(1L);
        employeeDTO.setEmployee_name("Santiago@");
        employeeDTO.setEmployee_department("RRHH@");
        employeeDTO.setEmployee_rfc("AUM470L29F37");
        employeeDTO.setEmployee_email("@gmail.com");
        employeeDTO.setEmployee_phone("571234567890");
        employeeDTO.setEmployee_address("calle de la amargura");
        employeeDTO.setEmployee_registration_date(new Date());
        employeeDTO.setEmployee_status(1L);
        employeeDTO.setSalary(80000L);
        return employeeDTO;
    }


    // Add Parameter to Transaction
    private void addParameter(final String parameter, final Object value) {
        final TransactionParameter tParameter = new TransactionParameter(parameter, value);
        transaction.getContext().getParameterList().put(parameter, tParameter);
    }

    // Get Parameter from Transaction
    private Object getParameter(final String parameter) {
        final TransactionParameter param = transaction.getContext().getParameterList().get(parameter);
        return param != null ? param.getValue() : null;
    }
}
