package com.mobileapps.reto2_appmov.pokeapi;

/**
 * las habilidades de un pokemon
 */
public class Abilities {

    // Atributos
    private Ability ability;

    // Constructores
    public Abilities() {
    }
    public Abilities(Ability ability) {
        this.ability = ability;
    }

    // Métodos
    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
