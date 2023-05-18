package com.pokemonurpg.entities.v1;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PokemonAttackKey implements Serializable {

    @Column(name = "species_dbid")
    private Integer pokemonDbid;

    @Column(name = "attack_dbid")
    private Integer attackDbid;

    public PokemonAttackKey() {    }

    public PokemonAttackKey(Integer pokemonDbid, Integer attackDbid) {
        this.pokemonDbid = pokemonDbid;
        this.attackDbid = attackDbid;
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public Integer getAttackDbid() {
        return attackDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonAttackKey)) return false;
        PokemonAttackKey that = (PokemonAttackKey) o;
        return Objects.equals(getPokemonDbid(), that.getPokemonDbid()) &&
                Objects.equals(getAttackDbid(), that.getAttackDbid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPokemonDbid(), getAttackDbid());
    }
}
