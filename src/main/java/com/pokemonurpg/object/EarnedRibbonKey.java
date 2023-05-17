package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EarnedRibbonKey implements Serializable {

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;

    @Column(name = "rank_dbid")
    private Integer rankDbid;

    @Column(name = "attribute_dbid")
    private Integer attributeDbid;

    public EarnedRibbonKey() {
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public void setPokemonDbid(Integer pokemonDbid) {
        this.pokemonDbid = pokemonDbid;
    }

    public Integer getRankDbid() {
        return rankDbid;
    }

    public void setRankDbid(Integer rankDbid) {
        this.rankDbid = rankDbid;
    }

    public Integer getAttributeDbid() {
        return attributeDbid;
    }

    public void setAttributeDbid(Integer attributeDbid) {
        this.attributeDbid = attributeDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EarnedRibbonKey that = (EarnedRibbonKey) o;
        return Objects.equals(pokemonDbid, that.pokemonDbid) &&
                Objects.equals(rankDbid, that.rankDbid) &&
                Objects.equals(attributeDbid, that.attributeDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonDbid, rankDbid, attributeDbid);
    }
}
