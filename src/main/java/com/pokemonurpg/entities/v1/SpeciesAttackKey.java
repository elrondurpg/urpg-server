package com.pokemonurpg.entities.v1;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpeciesAttackKey implements Serializable {

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    @Column(name = "attack_dbid")
    private Integer attackDbid;

    public SpeciesAttackKey() {    }

    public SpeciesAttackKey(Integer speciesDbid, Integer attackDbid) {
        this.speciesDbid = speciesDbid;
        this.attackDbid = attackDbid;
    }

    public Integer getSpeciesDbid() {
        return speciesDbid;
    }

    public Integer getAttackDbid() {
        return attackDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesAttackKey)) return false;
        SpeciesAttackKey that = (SpeciesAttackKey) o;
        return Objects.equals(getSpeciesDbid(), that.getSpeciesDbid()) &&
                Objects.equals(getAttackDbid(), that.getAttackDbid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpeciesDbid(), getAttackDbid());
    }
}
