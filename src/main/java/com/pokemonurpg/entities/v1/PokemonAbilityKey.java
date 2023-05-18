package com.pokemonurpg.entities.v1;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PokemonAbilityKey implements Serializable {

    @Column(name = "species_dbid")
    private Integer pokemonDbid;

    @Column(name = "ability_dbid")
    private Integer abilityDbid;

    public PokemonAbilityKey() {    }

    public PokemonAbilityKey(Integer pokemonDbid, Integer abilityDbid) {
        this.pokemonDbid = pokemonDbid;
        this.abilityDbid = abilityDbid;
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public Integer getAbilityDbid() {
        return abilityDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonAbilityKey)) return false;
        PokemonAbilityKey that = (PokemonAbilityKey) o;
        return Objects.equals(getPokemonDbid(), that.getPokemonDbid()) &&
                Objects.equals(getAbilityDbid(), that.getAbilityDbid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPokemonDbid(), getAbilityDbid());
    }

}
