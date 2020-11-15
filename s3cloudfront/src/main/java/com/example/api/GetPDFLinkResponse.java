package com.example.api;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GetPDFLinkResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    String responsStr;

    public String getResponsStr() {
        return this.responsStr;
    }

    public void setResponsStr(String responsStr) {
        this.responsStr = responsStr;
    }

}