package com.bbva.ccol.dto.employees.response;



import com.bbva.ccol.dto.employees.EmployeeDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ResponseEmployeeDTO implements Serializable {
    private static final long serialVersionUID = 2931699728946643245L;

    private List<EmployeeDTO> employeeDTOList;

    public List<EmployeeDTO> getEmployeeDTOList() {
        return employeeDTOList;
    }

    public PaginationOut paginationOut;

    public ResponseEmployeeDTO() {
        paginationOut = new PaginationOut();
    }

    public void setEmployeeDTOList(List<EmployeeDTO> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseEmployeeDTO that = (ResponseEmployeeDTO) o;
        return Objects.equals(employeeDTOList, that.employeeDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeDTOList);
    }

    @Override
    public String toString() {
        return "ResponseEmployeeDTO{" +
                "employeeDTOList=" + employeeDTOList +
                '}';
    }
}
