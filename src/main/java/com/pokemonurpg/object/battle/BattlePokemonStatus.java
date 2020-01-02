package com.pokemonurpg.object.battle;

import javax.persistence.*;

@Entity
public class BattlePokemonStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String label;

    @OneToOne
    @JoinColumn(name = "battle_pokemon_dbid")
    private BattlePokemon pokemon;

    @Column
    private int duration;

    @Column(name = "max_duration")
    private int maxDuration;

    public BattlePokemonStatus() {}
    public BattlePokemonStatus(String name) {
        this.label = name;
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String toString() {
        return "{ \"dbid\": " + dbid + ",\n\"label\": \"" + label + "\"}";
    }

    public BattlePokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(BattlePokemon pokemon) {
        this.pokemon = pokemon;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }
}
