package com.mobileapps.reto2_appmov.pokeapi;

public class Stats {

    private String base_stat;
    private Stat stat;

    public Stats() {
    }

    public Stats(String base_stat, Stat stat) {
        this.base_stat = base_stat;
        this.stat = stat;
    }

    public String getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(String base_stat) {
        this.base_stat = base_stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
