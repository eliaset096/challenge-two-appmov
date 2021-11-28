package com.mobileapps.reto2_appmov.pokeapi;

/**
 * Una habilidad de un pokemon
 */
public class Ability {

    // Atributos
    private String name;

    // Constructores
    public Ability() {
    }
    public Ability(String name) {
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
