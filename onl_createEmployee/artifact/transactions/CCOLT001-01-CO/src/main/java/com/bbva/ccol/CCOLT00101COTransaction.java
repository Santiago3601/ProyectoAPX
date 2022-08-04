package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.utils.DominosCorreos;
import com.bbva.ccol.dto.employee.utils.Validaciones;
import com.bbva.ccol.lib.r001.CCOLR001;
import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Método de creación de empleado para base de datos Oracle
 */
public class CCOLT00101COTransaction extends AbstractCCOLT00101COTransaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCOLT00101COTransaction.class);

    private static final String ADD_ADVICE_ERROR = "CCOL00010001";

    private StringBuilder respuesta;


    /**
     * The execute method...
     */
    @Override
    public void execute() {
        CCOLR001 ccolR001 = this.getServiceLibrary(CCOLR001.class);
        EmployeeDTO employeeDTO = this.getEmployeedto();
        Advice advice = null;
        if (validacionCampos(employeeDTO)) {
            ccolR001.executeCreateCustomer(employeeDTO);
            advice = this.getAdvice();  // ADVICE = NULL, NO HAY ERROR


            if (advice != null) {
                LOGGER.error("ERROR: AL CREAR EL EMPLEADO EN BD...");
                setSeverity(Severity.EWR);
            }
        } else {
            LOGGER.info("ERROR AL VALIDAR CAMPOS DE ENTRADA");

            // Rango de error asignado al aplicativo ejemplo: (00010001 al 00010101
            // asignar el codigo de error : UUAA + 8 NUMEROS
            addAdvice(ADD_ADVICE_ERROR);
            setSeverity(Severity.WARN);
        }
    }

    private boolean validacionCampos(EmployeeDTO employeeDTO) {

        return true;
    }
/*
    private boolean validacionCampos(EmployeeDTO employeeDTO) {
        boolean resultado = true;

        //Validación  nombre del empleado
        if (!validacionCaracteres(employeeDTO.getEmployee_name())) {
            this.respuesta.append("Caracteres especiales en nombre del empleado \n");
            resultado = false;
        }

        //Validación  departamento del empleado
        if (!validacionCaracteres(employeeDTO.getEmployee_department())) {
            this.respuesta.append("Caracteres especiales en departamento del empleado \n");
            resultado = false;
        }

        //Validación de RFC
        //Validar tamaño del RFC
        String rfc =employeeDTO.getEmployee_RFC().trim();
        boolean errorRFC=false;
        if (rfc.length() == Validaciones.RFC_PER.getValues()){
            //Válida que las primeras cuatro letras solo sean letras
            if (rfc.substring(0,3).matches("[0-9]+")) {
                this.respuesta.append("Error al validar estructura RFC \n");
                errorRFC=true;
            }
            //Válida que los 6 siguientes caracteres sean numéricos
            if (rfc.substring(4,10).matches("[a-zA-Z]")&&!errorRFC) {
                this.respuesta.append("Error al validar estructura RFC \n");
                errorRFC=true;
            }


        } else if (rfc.length() == Validaciones.RFC_EMP.getValues()) {
            //Válida que las primeras cuatro letras solo sean letras
            if (rfc.substring(0,4).matches("[0-9]+")) {
                this.respuesta.append("Error al validar estructura RFC \n");
                errorRFC=true;
            }
            //Válida que los 6 siguientes caracteres sean numéricos
            if (rfc.substring(3,9).matches("[a-zA-Z]")&&!errorRFC) {
                this.respuesta.append("Error al validar estructura RFC \n");
                errorRFC=true;
            }
        }else{
            this.respuesta.append("Error al validar tamaño de RFC \n");
            errorRFC=true;
        }
        if(errorRFC){
            resultado = false;
        }

        //Validar caracteres especiales
        if (!validacionCaracteres(employeeDTO.getEmployee_RFC())) {
            this.respuesta.append("Caracteres especiales en RFC del empleado \n");
            resultado = false;
        }

        //Validación departamento del empleado
        if (!validacionCaracteres(employeeDTO.getEmployee_department())) {
            this.respuesta.append("Caracteres especiales en departamento del empleado \n");
            resultado = false;
        }

        //Validación del correo
        //Validar estructura del correo
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(employeeDTO.getEmployee_email());
        if (!matcher.matches()) {
            this.respuesta.append("Error en estructura de correo \n");
        }
        boolean dominioCorreo = false;
        //Iterar por los dominios de correos permitidos en busqueda del que el ingresado sea valido
        for (DominosCorreos mail : DominosCorreos.values()) {
            if (employeeDTO.getEmployee_email().contains(mail.getValues())) {
                dominioCorreo = true;
                break;
            }
        }
        if (!dominioCorreo) {
            this.respuesta.append("El dominio del correo no es valido \n");
            resultado = false;
        }

        //Validar número de telefono

        //Validación dirección del empleado


        return resultado;
    }
*/
    private boolean validacionCaracteres(String cadena) {
        return cadena.matches("^[a-zA-Z0-9]+$");
    }


}