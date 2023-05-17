package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MegaEvolutionKey implements Serializable
{
    @Column(name = "mega_dbid")
    Integer megaEvolutionDbid;

    @Column(name = "original_dbid")
    Integer originalDbid;

    public MegaEvolutionKey() {
    }

    public MegaEvolutionKey(Integer megaEvolutionDbid, Integer originalDbid) {
        this.megaEvolutionDbid = megaEvolutionDbid;
        this.originalDbid = originalDbid;
    }

    public Integer getMegaEvolutionDbid() {
        return megaEvolutionDbid;
    }

    public void setMegaEvolutionDbid(Integer megaEvolutionDbid) {
        this.megaEvolutionDbid = megaEvolutionDbid;
    }

    public Integer getOriginalDbid() {
        return originalDbid;
    }

    public void setOriginalDbid(Integer originalDbid) {
        this.originalDbid = originalDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MegaEvolutionKey that = (MegaEvolutionKey) o;
        return megaEvolutionDbid.equals(that.megaEvolutionDbid) &&
                originalDbid.equals(that.originalDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(megaEvolutionDbid, originalDbid);
    }
}
