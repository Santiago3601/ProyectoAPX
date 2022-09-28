package com.bbva.ccol.batch;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.lib.r007.CCOLR007;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReaderCreate implements ItemReader<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReaderCreate.class);

    private CCOLR007 ccolR007;

    public CCOLR007 getCcolR007() {
        return ccolR007;
    }

    public void setCcolR007(CCOLR007 ccolR007) {
        this.ccolR007 = ccolR007;
    }

    private Integer pageKey = 1;
    private Integer pageSize = 5;

    private Integer cont = 0;

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String oDate;

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        List<EmployeeDTO> response = ccolR007.executeListCustomer();
        String listaDep = ccolR007.executeDistinctDepartment();
        String[] list = listaDep.split(",");
        StringBuilder lista = new StringBuilder();
        boolean entro = false;
        for (int i = 0; i < list.length; i++) {
            if (i == cont && !entro) {
                String d = list[cont].replace("'", "");
                lista = recorridoMapa(response.stream().filter(e -> d.equalsIgnoreCase(e.getEmployee_department())).collect(Collectors.toList()));
                entro = true;
                cont++;
            }
        }
        LOGGER.info("CONTADOR : {}", cont);
        LOGGER.info("LISTA LENGTH : {}", list.length);
        if (!entro) {
            LOGGER.info("SALIDA READ");
            return null;
        }
        List<StringBuilder> listaGeneral = new ArrayList<>();
        listaGeneral.add(lista);
        LOGGER.info("LISTA GENERAL : {}", listaGeneral.toString());
        return listaGeneral;
    }

    public StringBuilder recorridoMapa(List<EmployeeDTO> lista) {
        StringBuilder listaBuilder = new StringBuilder();
        for (EmployeeDTO mapa : lista) {
            listaBuilder.append(mapa.getEmployee_name() + ",");
            listaBuilder.append(mapa.getEmployee_department() + ",");
            listaBuilder.append(mapa.getEmployee_rfc() + ",");
            listaBuilder.append(mapa.getEmployee_email() + ",");
            listaBuilder.append(mapa.getEmployee_phone() + ",");
            listaBuilder.append(mapa.getEmployee_address() + ",");
            listaBuilder.append(mapa.getEmployee_registration_date() + ",");
            listaBuilder.append(mapa.getEmployee_status() + ",");
            listaBuilder.append(mapa.getSalary() + "\n");
        }
        return listaBuilder;
    }

}
