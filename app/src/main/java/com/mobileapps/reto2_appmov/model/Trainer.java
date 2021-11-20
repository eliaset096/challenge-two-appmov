package com.mobileapps.reto2_appmov.model;

import java.io.Serializable;

/**
 * Entidad que representa un entrenador
 */
public class Trainer implements Serializable {

    // Atributos
    private String id;
    private String name;

    // Construcotres
    public Trainer() {
    }
    public Trainer(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // Métodos
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
