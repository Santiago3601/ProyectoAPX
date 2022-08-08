package com.bbva.ccol;

import com.bbva.ccol.dto.employee.EmployeeDTO;
import com.bbva.ccol.dto.employee.utils.DominosCorreos;
import com.bbva.ccol.dto.employee.utils.Validaciones;
import com.bbva.ccol.lib.r003.CCOLR003;
import com.bbva.elara.domain.transaction.Advice;
import com.bbva.elara.domain.transaction.Severity;
import com.bbva.elara.domain.transaction.response.HttpResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Update Transaction
 *
 */
public class CCOLT00301COTransaction extends AbstractCCOLT00301COTransaction {

	private static final Logger LOGGER = LoggerFactory.getLogger(CCOLT00301COTransaction.class);
	private StringBuilder respuesta ;
	/**
	 * The execute method...
	 */
	@Override
	public void execute() {
		CCOLR003 ccolR003 = this.getServiceLibrary(CCOLR003.class);
		respuesta = new StringBuilder();
		EmployeeDTO employeeDTO = limpiarEspacios(this.getEmployeedto());
		LOGGER.info("OBJETO CUSTOMER : {}", employeeDTO.toString());

		if (validacionCampos(employeeDTO)) {
			this.respuesta.append("La estructura ingresada es correcta");
			cambiarTelefono(employeeDTO);

			ccolR003.executeUpdateEmployee(employeeDTO);
			List<Advice> adviceList = this.getAdviceList();
			if(adviceList == null){
				LOGGER.info("reporte exitoso");
				setSeverity(Severity.OK);
			}else {
				for (Advice adv : adviceList
				) {
					switch (adv.getCode()) {
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
						default:
							break;
					}
				}
			}

		}
		//Devolver mensaje
		this.setMensaje("1");
		LOGGER.info("Advice {}",this.getAdvice());

//        System.out.println("----------------------");
//        System.out.println(this.respuesta.toString());


	}


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
		String rfc = employeeDTO.getEmployee_rfc().trim();
		if (rfc.length() == Validaciones.RFC_PER.getValues()) {
			//Válida que las primeras cuatro letras solo sean letras
			if (rfc.substring(0, 3).matches("(.*)[0-9]+(.*)")) {
				this.respuesta.append("No se permiten números en el nombre dentro de la estructura RFC \n");
				resultado = false;
			}
			//Válida que los 6 siguientes caracteres sean numéricos
			if (rfc.substring(4, 10).matches("(.*)[a-zA-Z](.*)")) {
				this.respuesta.append("Se encontraron caracteres dentro de la fecha de nacimiento en la estructura RFC \n");
				resultado = false;
			}


		} else if (rfc.length() == Validaciones.RFC_EMP.getValues()) {
			//Válida que las primeras cuatro letras solo sean letras
			if (rfc.substring(0, 3).matches("(.*)[0-9]+(.*)")) {
				this.respuesta.append("No se permiten números en el nombre dentro de la estructura RFC \n");
				resultado = false;
			}
			//Válida que los 6 siguientes caracteres sean numéricos
			if (rfc.substring(3, 9).matches("(.*)[a-zA-Z](.*)")) {
				this.respuesta.append("Se encontraron caracteres dentro de la fecha de creación en la estructura RFC \n");
				resultado = false;
			}
		} else {
			this.respuesta.append("Error al validar tamaño de RFC \n");
			resultado = false;

		}


		//Validar caracteres especiales
		if (!validacionCaracteres(employeeDTO.getEmployee_rfc())) {
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
			resultado = false;
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

		//Validar número de teléfono

		if ( employeeDTO.getEmployee_phone().length()!= Validaciones.CEL_CON_LADA.getValues()&&employeeDTO.getEmployee_phone().length() != Validaciones.CEL_SIN_LADA.getValues()) {
			this.respuesta.append("El telefono no cumple con el tamaño indicado \n");
			resultado = false;
		}

//Validación dirección del empleado


		return resultado;
	}

	private boolean validacionCaracteres(String cadena) {
		return cadena.matches("^[a-zA-Z0-9\\s]+$");
	}

	private EmployeeDTO limpiarEspacios(EmployeeDTO employeeDTO){
		employeeDTO.setEmployee_name(employeeDTO.getEmployee_name().trim());
		employeeDTO.setEmployee_department(employeeDTO.getEmployee_department().trim());
		employeeDTO.setEmployee_rfc(employeeDTO.getEmployee_rfc().trim());
		employeeDTO.setEmployee_email(employeeDTO.getEmployee_email().trim());
		employeeDTO.setEmployee_phone(employeeDTO.getEmployee_phone().trim());
		employeeDTO.setEmployee_address(employeeDTO.getEmployee_address().trim());
		return employeeDTO;
	}
	private EmployeeDTO cambiarTelefono(EmployeeDTO employeeDTO){
		String anteriortelefono =employeeDTO.getEmployee_phone();

		if (anteriortelefono.length()== Validaciones.CEL_SIN_LADA.getValues()) {
			employeeDTO.setEmployee_phone(Validaciones.LADA_DEF.getValues() + employeeDTO.getEmployee_phone());
		}
		employeeDTO.setEmployee_phone("+"+employeeDTO.getEmployee_phone());
		return employeeDTO;

	}

}
