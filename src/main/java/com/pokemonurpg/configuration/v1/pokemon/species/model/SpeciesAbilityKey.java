package com.pokemonurpg.configuration.v1.pokemon.species.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SpeciesAbilityKey implements Serializable {

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    @Column(name = "ability_dbid")
    private Integer abilityDbid;

    public SpeciesAbilityKey() {    }

    public SpeciesAbilityKey(Integer speciesDbid, Integer abilityDbid) {
        this.speciesDbid = speciesDbid;
        this.abilityDbid = abilityDbid;
    }

    public Integer getSpeciesDbid() {
        return speciesDbid;
    }

    public Integer getAbilityDbid() {
        return abilityDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesAbilityKey)) return false;
        SpeciesAbilityKey that = (SpeciesAbilityKey) o;
        return Objects.equals(getSpeciesDbid(), that.getSpeciesDbid()) &&
                Objects.equals(getAbilityDbid(), that.getAbilityDbid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpeciesDbid(), getAbilityDbid());
    }

}
