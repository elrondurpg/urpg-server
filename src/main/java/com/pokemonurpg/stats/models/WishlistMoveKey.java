package com.pokemonurpg.stats.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WishlistMoveKey implements Serializable {

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "move_dbid")
    private Integer moveDbid;

    public WishlistMoveKey() {

    }

    public WishlistMoveKey(Integer pokemonDbid, Integer moveDbid) {
        this.pokemonDbid = pokemonDbid;
        this.moveDbid = moveDbid;
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public void setPokemonDbid(Integer pokemonDbid) {
        this.pokemonDbid = pokemonDbid;
    }

    public Integer getMoveDbid() {
        return moveDbid;
    }

    public void setMoveDbid(Integer moveDbid) {
        this.moveDbid = moveDbid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((moveDbid == null) ? 0 : moveDbid.hashCode());
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
        WishlistMoveKey other = (WishlistMoveKey) obj;
        if (moveDbid == null) {
            if (other.moveDbid != null)
                return false;
        } else if (!moveDbid.equals(other.moveDbid))
            return false;
        if (pokemonDbid == null) {
            if (other.pokemonDbid != null)
                return false;
        } else if (!pokemonDbid.equals(other.pokemonDbid))
            return false;
        return true;
    }

    
}

