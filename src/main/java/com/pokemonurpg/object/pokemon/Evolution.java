package com.pokemonurpg.object.pokemon;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Evolution {

    @EmbeddedId
    EvolutionKey id;

    @Column(name = "evolution_method")
    String method;

    @Column(name = "num_battles")
    int numBattles;

    public Evolution() {
    }

    public Evolution(int evoDbid, int prevoDbid, String method) {
        setId(new EvolutionKey(evoDbid, prevoDbid));
        this.method = method;
    }

    public EvolutionKey getId() {
        return id;
    }

    public void setId(EvolutionKey id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getNumBattles() {
        return numBattles;
    }

    public void setNumBattles(int numBattles) {
        this.numBattles = numBattles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evolution evolution = (Evolution) o;
        return id.equals(evolution.id) &&
                method.equals(evolution.method) &&
                numBattles == evolution.numBattles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method, numBattles);
    }
}
