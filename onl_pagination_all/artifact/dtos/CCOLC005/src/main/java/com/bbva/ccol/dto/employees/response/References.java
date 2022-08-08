package com.bbva.ccol.dto.employees.response;

import java.util.Objects;

public class References {

    private String lastPage;
    private String nextPage;
    private String previousPage;

    @Override
    public String toString() {
        return "References{" +
                "lastPage='" + lastPage + '\'' +
                ", nextPage='" + nextPage + '\'' +
                ", previousPage='" + previousPage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof References)) return false;
        References that = (References) o;
        return Objects.equals(lastPage, that.lastPage) && Objects.equals(nextPage, that.nextPage) && Objects.equals(previousPage, that.previousPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastPage, nextPage, previousPage);
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }
}
