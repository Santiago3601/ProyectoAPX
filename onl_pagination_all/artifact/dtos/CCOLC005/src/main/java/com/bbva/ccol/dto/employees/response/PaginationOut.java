package com.bbva.ccol.dto.employees.response;

import java.util.Objects;

public class PaginationOut {

    private References references;
    private Long page;
    private Long totalPages;
    private Long totalElements;
    private Long pageSize;

    public PaginationOut() {
        references = new References();
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
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
        if (!(o instanceof PaginationOut)) return false;
        PaginationOut that = (PaginationOut) o;
        return Objects.equals(references, that.references) && Objects.equals(page, that.page) && Objects.equals(totalPages, that.totalPages) && Objects.equals(totalElements, that.totalElements) && Objects.equals(pageSize, that.pageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(references, page, totalPages, totalElements, pageSize);
    }

    @Override
    public String toString() {
        return "PaginationOut{" +
                "references=" + references +
                ", page=" + page +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", pageSize=" + pageSize +
                '}';
    }
}
