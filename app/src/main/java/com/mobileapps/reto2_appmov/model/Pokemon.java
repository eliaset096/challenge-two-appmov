package com.mobileapps.reto2_appmov.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entidad que representa un pokemon
 */
public class Pokemon implements Serializable {

    // Atributos
    private String id;
    private String avatarUri;
    private String name;
    private String ability;
    private String defense;
    private String attack;
    private String speed;
    private String life;
    private int cathingOrder;

    // Constructores
    public Pokemon() {
    }
    public Pokemon(String id, String avatarUri, String name, String ability, String defense, String attack, String speed, String life, int cathingOrder) {
        this.id = id;
        this.avatarUri = avatarUri;
        this.name = name;
        this.ability = ability;
        this.defense = defense;
        this.attack = attack;
        this.speed = speed;
        this.life = life;
        this.cathingOrder = cathingOrder;
    }

    // Métodos
    public String getId() {
        return id;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLife() {
        return life;
    }

    public void setLife(String life) {
        this.life = life;
    }

    public int getCathingOrder() {
        return cathingOrder;
    }

    public void setCathingOrder(int cathingOrder) {
        this.cathingOrder = cathingOrder;
    }



}
