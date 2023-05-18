package com.pokemonurpg.entities.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WishlistAbilityKey implements Serializable {

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "ability_dbid")
    private Integer abilityDbid;

    public WishlistAbilityKey() {

    }

    public WishlistAbilityKey(Integer pokemonDbid, Integer abilityDbid) {
        this.pokemonDbid = pokemonDbid;
        this.abilityDbid = abilityDbid;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((abilityDbid == null) ? 0 : abilityDbid.hashCode());
        result = prime * result + ((pokemonDbid == null) ? 0 : pokemonDbid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WishlistAbilityKey other = (WishlistAbilityKey) obj;
        if (abilityDbid == null) {
            if (other.abilityDbid != null)
                return false;
        } else if (!abilityDbid.equals(other.abilityDbid))
            return false;
        if (pokemonDbid == null) {
            if (other.pokemonDbid != null)
                return false;
        } else if (!pokemonDbid.equals(other.pokemonDbid))
            return false;
        return true;
    }

    
}

