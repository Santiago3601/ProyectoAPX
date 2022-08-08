package com.bbva.ccol.dto.employee;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The EmployeeDTO class...
 */
public class EmployeeDTO implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	/* Attributes section for the DTO */

	private Long employee_number;
	private String employee_name;
	private String employee_department;
	private String employee_rfc;
	private String employee_email;
	private String employee_phone;
	private String employee_address;
	private Date employee_registration_date;
	private Long employee_status;
	private Long salary;
	/**
	 * Getters and Setters.
	 */
	public Long getEmployee_number() {
		return employee_number;
	}

	public void setEmployee_number(Long employee_number) {
		this.employee_number = employee_number;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_department() {
		return employee_department;
	}

	public void setEmployee_department(String employee_department) {
		this.employee_department = employee_department;
	}

	public String getEmployee_rfc() {
		return employee_rfc;
	}

	public void setEmployee_rfc(String employee_rfc) {
		this.employee_rfc = employee_rfc;
	}

	public String getEmployee_email() {
		return employee_email;
	}

	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}

	public String getEmployee_phone() {
		return employee_phone;
	}

	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}

	public String getEmployee_address() {
		return employee_address;
	}

	public void setEmployee_address(String employee_address) {
		this.employee_address = employee_address;
	}

	public Date getEmployee_registration_date() {
		return employee_registration_date;
	}

	public void setEmployee_registration_date(Date employee_registration_date) {
		this.employee_registration_date = employee_registration_date;
	}

	public Long getEmployee_status() {
		return employee_status;
	}

	public void setEmployee_status(Long employee_status) {
		this.employee_status = employee_status;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		final EmployeeDTO rhs = (EmployeeDTO) obj;
		return new EqualsBuilder().appendSuper(super.equals(obj))
				.append(employee_number, rhs.employee_number)
				.append(employee_name, rhs.employee_name)
				.append(employee_department, rhs.employee_department)
				.append(employee_rfc, rhs.employee_rfc)
				.append(employee_email, rhs.employee_email)
				.append(employee_phone, rhs.employee_phone)
				.append(employee_address, rhs.employee_address)
				.append(employee_registration_date, rhs.employee_registration_date)
				.append(employee_status, rhs.employee_status)
				.append(salary, rhs.salary)
				.isEquals();
	}
	/**
	 * Returns a hash code value for the object.
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.employee_number)
				.append(this.employee_name)
				.append(this.employee_department)
				.append(this.employee_rfc)
				.append(this.employee_email)
				.append(this.employee_phone)
				.append(this.employee_address)
				.append(this.employee_registration_date)
				.append(this.employee_status)
				.append(this.salary)
				.toHashCode();
	}
	/**
	 * Returns a string representation of the object.
	 * It is important to OBFUSCATE the attributes that are sensitive to show in the representation.
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("employee_number", employee_number)
				.append("employee_name", employee_name)
				.append("employee_department", employee_department)
				.append("employee_rfc", employee_rfc)
				.append("employee_email", employee_email)
				.append("employee_phone", employee_phone)
				.append("employee_address", employee_address)
				.append("employee_registration_date", employee_registration_date)
				.append("employee_status", employee_status)
				.append("salary", salary)
				.toString();
	}
}
