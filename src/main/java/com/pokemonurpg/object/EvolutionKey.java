package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EvolutionKey implements Serializable
{
    @Column(name = "evolution_dbid")
    Integer evolutionDbid;

    @Column(name = "pre_evolution_dbid")
    Integer preEvolutionDbid;

    public EvolutionKey() {
    }

    public EvolutionKey(Integer evolutionDbid, Integer preEvolutionDbid) {
        this.evolutionDbid = evolutionDbid;
        this.preEvolutionDbid = preEvolutionDbid;
    }

    public Integer getEvolutionDbid() {
        return evolutionDbid;
    }

    public void setEvolutionDbid(Integer evolutionDbid) {
        this.evolutionDbid = evolutionDbid;
    }

    public Integer getPreEvolutionDbid() {
        return preEvolutionDbid;
    }

    public void setPreEvolutionDbid(Integer preEvolutionDbid) {
        this.preEvolutionDbid = preEvolutionDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvolutionKey that = (EvolutionKey) o;
        return evolutionDbid.equals(that.evolutionDbid) &&
                preEvolutionDbid.equals(that.preEvolutionDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evolutionDbid, preEvolutionDbid);
    }
}
