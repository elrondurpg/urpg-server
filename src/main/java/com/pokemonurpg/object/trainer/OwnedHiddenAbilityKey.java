package com.pokemonurpg.object.trainer;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnedHiddenAbilityKey implements Serializable {
    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "ability_dbid")
    private Integer abilityDbid;

    public OwnedHiddenAbilityKey() {
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public void setPokemonDbid(Integer pokemonDbid) {
        this.pokemonDbid = pokemonDbid;
    }

    public Integer getAbilityDbid() {
        return abilityDbid;
    }

    public void setAbilityDbid(Integer abilityDbid) {
        this.abilityDbid = abilityDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedHiddenAbilityKey that = (OwnedHiddenAbilityKey) o;
        return Objects.equals(pokemonDbid, that.pokemonDbid) &&
                Objects.equals(abilityDbid, that.abilityDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonDbid, abilityDbid);
    }
}
