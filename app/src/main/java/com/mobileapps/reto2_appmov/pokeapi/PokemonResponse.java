package com.mobileapps.reto2_appmov.pokeapi;

public class PokemonResponse {

    private String id;
    private String name;
    private Abilities[] abilities;
    private Stats[] stats;
    private Sprites sprites;

    public PokemonResponse() {
    }

    public PokemonResponse(String id, String name, Abilities[] abilities, Stats[] stats, Sprites sprites) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
        this.stats = stats;
        this.sprites = sprites;
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

    public Abilities[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Abilities[] abilities) {
        this.abilities = abilities;
    }

    public Stats[] getStats() {
        return stats;
    }

    public void setStats(Stats[] stats) {
        this.stats = stats;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }
}
