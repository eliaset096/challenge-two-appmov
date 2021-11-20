package com.mobileapps.reto2_appmov.pokeapi;

/**
 * Son las fotos de un pokemon
 */
public class Sprites {

    // Atributos
    private String front_default;

    // Construcores
    public Sprites() {
    }
    public Sprites(String front_default) {
        this.front_default = front_default;
    }

    // Métodos
    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}
