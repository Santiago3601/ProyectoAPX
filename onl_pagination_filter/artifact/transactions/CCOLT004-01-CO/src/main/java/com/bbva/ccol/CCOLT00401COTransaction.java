package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.pagination.PaginationIn;
import com.bbva.ccol.dto.employee.response.PaginationOut;
import com.bbva.ccol.dto.employee.response.References;
import com.bbva.ccol.lib.r004.CCOLR004;
import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Pagination filtered by name - Transaction
 *
 */
public class CCOLT00401COTransaction extends AbstractCCOLT00401COTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCOLT00401COTransaction.class);


	private static final int DEFAULT_PAGE_SIZE = 10;
	private static final int DEFAULT_PAGINATION_KEY = 0;
	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		CCOLR004 ccolR004 = this.getServiceLibrary(CCOLR004.class);
		// TODO - Implementation of business logic

		PaginationIn paginationIn = this.getPaginationin();
		EmployeeDTO employeeDTO = this.getEmployeedto();
		int page = (StringUtils.isNotBlank(paginationIn.getPaginationKey()) || Integer.parseInt(paginationIn.getPaginationKey())>0 ? Integer.parseInt(paginationIn.getPaginationKey()) : DEFAULT_PAGINATION_KEY);
		Integer limitRows = (paginationIn.getPageSize()>0 ? Integer.parseInt(paginationIn.getPageSize().toString()) : DEFAULT_PAGE_SIZE);
		paginationIn.setPaginationKey(String.valueOf(page));
		paginationIn.setPageSize(Long.parseLong(limitRows.toString()));

		LOGGER.info("DATOS INGRESO : {}", paginationIn);
		List<EmployeeDTO> response = new ArrayList<>();
		response = ccolR004.executeListCustomer(paginationIn,employeeDTO);

		Long cantidadCustomers = Long.parseLong(ccolR004.executeListEmployeePaginacion(employeeDTO).toString());
		LOGGER.info("CANTIDAD EMPLEADOS : {}", cantidadCustomers);
		PaginationOut pagination = createPagination(Long.parseLong(String.valueOf(page)), Long.parseLong(String.valueOf(limitRows)), cantidadCustomers);

		List<Advice> adviceList = this.getAdviceList();
		if(adviceList == null){
			LOGGER.info("reporte exitoso");
			setSeverity(Severity.OK);
		}else{
			for (Advice adv : adviceList
			) {
				switch (adv.getCode()){
					case "CAPX00000001":
						//rollback
						this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400, Severity.EWR);
						break;
					case "CAPX00000002":
//					nosotros decidimos
						this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400, Severity.EWR);
						break;
					case "CAPX00000003":
//					error sin rollback
						this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400, Severity.ENR);
						break;
					case "CAPX00000004":
//					error sin rollback
						this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_204, Severity.OK);
						break;
					case "CAPX00000005":
//					error sin rollback
						this.setHttpResponseCode(HttpResponseCode.HTTP_CODE_400, Severity.ENR);
						break;
					default:
						break;
				}
			}
		}

		LOGGER.info("respuesta: {}", response);

		this.setEmployeedtolist(response);
		this.setPaginationout(pagination);
	}

	private PaginationOut createPagination(long paginationKey, long pageSize, long total) {
		LOGGER.info("[PAPXR001] createPagination ..... ");
		long pFirst = 0;
		long pNext = paginationKey + 1;
		double ope = (total / pageSize);
		int pLast = (int) Math.ceil(ope); // page: 0, 1, 2, 3 ,4 ,5, 6, ...
		long pPrevious = paginationKey - 1;
		LOGGER.info("PRIMERA PAGINA    : {}", pFirst);
		LOGGER.info("PAGINA SIGUIENTE     : {}", pNext);
		LOGGER.info("ULTIMA PAGINA     : {}", pLast);
		LOGGER.info("PAGINA ANTERIOR : {}", pPrevious);
		References references = new References();
//		paginationLinks.setLastPage((long) pFirst);
		references.setLastPage(String.valueOf (pLast - 1));
		if (pNext<pLast) references.setNextPage(String.valueOf(pNext));
		if (pPrevious>=0) references.setPreviousPage(String.valueOf( pPrevious));
		LOGGER.info("[PGLUT4] DTOPaginationLinks : {}", references);
		PaginationOut paginationNode = new PaginationOut();
		paginationNode.setPageSize((long) pageSize);
		paginationNode.setPage((long) paginationKey);
		paginationNode.setTotalElements((long) total);
		paginationNode.setTotalPages((long) pLast);
		paginationNode.setReferences(references);
		LOGGER.info("[PGLUT4] DTOPaginationOut : {}", paginationNode);
		return paginationNode;
	}

}
