package com.mobileapps.reto2_appmov.pokeapi;

/**
 * Una estadística de un pokemon
 */
public class Stat {

    // Atributos
    private String name;

    // Construcotores
    public Stat() {
    }
    public Stat(String name) {
        this.name = name;
    }

    // Métodos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
