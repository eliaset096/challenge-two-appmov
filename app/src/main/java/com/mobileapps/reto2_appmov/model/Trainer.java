package com.mobileapps.reto2_appmov.model;

import java.io.Serializable;

public class Trainer implements Serializable {
    private String id;
    private String name;

    public Trainer() {
    }

    public Trainer(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
