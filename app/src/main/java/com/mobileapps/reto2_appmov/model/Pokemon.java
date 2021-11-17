package com.mobileapps.reto2_appmov.model;

public class Pokemon {

    private String avatarUri;
    private String name;
    private String ability;
    private String defense;
    private String attack;
    private String speed;
    private String life;

    public Pokemon() {
    }

    public Pokemon(String avatarUri, String name, String ability, String defense, String attack, String speed, String life) {
        this.avatarUri = avatarUri;
        this.name = name;
        this.ability = ability;
        this.defense = defense;
        this.attack = attack;
        this.speed = speed;
        this.life = life;
    }
}
