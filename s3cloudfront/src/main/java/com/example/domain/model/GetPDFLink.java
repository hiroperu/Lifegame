package com.example.domain.model;

import java.io.Serializable;
import java.io.File;
import java.util.Date;

public class GetPDFLink implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;
    private String pubKey;
    private String item1;
    private String item2;
    private Date createdAt;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubKey() {
        return this.pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getItem1() {
        return this.item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return this.item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}