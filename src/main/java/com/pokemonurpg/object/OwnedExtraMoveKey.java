package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnedExtraMoveKey implements Serializable {
    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "attack_dbid")
    private Integer attackDbid;

    public OwnedExtraMoveKey() {
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public void setPokemonDbid(Integer pokemonDbid) {
        this.pokemonDbid = pokemonDbid;
    }

    public Integer getAttackDbid() {
        return attackDbid;
    }

    public void setAttackDbid(Integer attackDbid) {
        this.attackDbid = attackDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedExtraMoveKey that = (OwnedExtraMoveKey) o;
        return Objects.equals(pokemonDbid, that.pokemonDbid) &&
                Objects.equals(attackDbid, that.attackDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonDbid, attackDbid);
    }
}
