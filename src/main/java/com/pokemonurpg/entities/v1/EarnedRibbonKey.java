package com.pokemonurpg.entities.v1;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EarnedRibbonKey implements Serializable {

    @Column(name = "log_url")
    private String logUrl;

    @Column(name = "pokemon_dbid")
    private Integer pokemonDbid;
    
    @Column(name = "contest_type_dbid")
    private Integer generationDbid;

    @Column(name = "rank_dbid")
    private Integer rankDbid;

    @Column(name = "attribute_dbid")
    private Integer attributeDbid;

    public EarnedRibbonKey() {    }

    public EarnedRibbonKey(String logUrl, Integer pokemonDbid, Integer generationDbid, Integer rankDbid,
            Integer attributeDbid) {
        this.logUrl = logUrl;
        this.pokemonDbid = pokemonDbid;
        this.generationDbid = generationDbid;
        this.rankDbid = rankDbid;
        this.attributeDbid = attributeDbid;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public Integer getPokemonDbid() {
        return pokemonDbid;
    }

    public void setPokemonDbid(Integer pokemonDbid) {
        this.pokemonDbid = pokemonDbid;
    }

    public Integer getGenerationDbid() {
        return generationDbid;
    }

    public void setGenerationDbid(Integer generationDbid) {
        this.generationDbid = generationDbid;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attributeDbid == null) ? 0 : attributeDbid.hashCode());
        result = prime * result + ((generationDbid == null) ? 0 : generationDbid.hashCode());
        result = prime * result + ((logUrl == null) ? 0 : logUrl.hashCode());
        result = prime * result + ((pokemonDbid == null) ? 0 : pokemonDbid.hashCode());
        result = prime * result + ((rankDbid == null) ? 0 : rankDbid.hashCode());
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
        EarnedRibbonKey other = (EarnedRibbonKey) obj;
        if (attributeDbid == null) {
            if (other.attributeDbid != null)
                return false;
        } else if (!attributeDbid.equals(other.attributeDbid))
            return false;
        if (generationDbid == null) {
            if (other.generationDbid != null)
                return false;
        } else if (!generationDbid.equals(other.generationDbid))
            return false;
        if (logUrl == null) {
            if (other.logUrl != null)
                return false;
        } else if (!logUrl.equals(other.logUrl))
            return false;
        if (pokemonDbid == null) {
            if (other.pokemonDbid != null)
                return false;
        } else if (!pokemonDbid.equals(other.pokemonDbid))
            return false;
        if (rankDbid == null) {
            if (other.rankDbid != null)
                return false;
        } else if (!rankDbid.equals(other.rankDbid))
            return false;
        return true;
    }
}
