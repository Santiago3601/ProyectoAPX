package com.bbva.ccol.dto.employee.utils;

public enum DominosCorreos {
    OUTLOOK_COM("@outlook.com"),
    GMAIL_COM("@gmail.com"),
   ;

    private String mails;

    public String getValues() {
        return mails;
    }
    DominosCorreos(String mails) {
        this.mails = mails;

    }
}
