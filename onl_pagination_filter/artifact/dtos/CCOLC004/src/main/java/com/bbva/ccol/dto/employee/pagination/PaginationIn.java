package com.bbva.ccol.dto.employee.pagination;

import java.util.Objects;

public class PaginationIn {

    private String paginationKey;
    private Long pageSize;


    public String getPaginationKey() {
        return paginationKey;
    }

    public void setPaginationKey(String paginationKey) {
        this.paginationKey = paginationKey;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginationIn that = (PaginationIn) o;
        return Objects.equals(paginationKey, that.paginationKey) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paginationKey, pageSize);
    }

    @Override
    public String toString() {
        return "PaginationIn{" +
                ", paginationKey='" + paginationKey + '\'' +
                ", pageSize=" + pageSize +
                '}';
    }
}
