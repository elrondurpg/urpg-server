package com.pokemonurpg.object.pokemon;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "species_ability")
public class SpeciesAbility {

    @EmbeddedId
    SpeciesAbilityKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private Species species;

    @ManyToOne
    @MapsId("ability_dbid")
    @JoinColumn(name = "ability_dbid")
    private Ability ability;

    @Column(name = "is_hidden")
    private Boolean hidden;

    public SpeciesAbility() {    }

    public SpeciesAbility(int speciesDbid, int abilityDbid, Boolean hidden) {
        this.id = new SpeciesAbilityKey(speciesDbid, abilityDbid);
        this.hidden = hidden;
    }

    public void cloneValuesFrom(SpeciesAbility record) {
        if (id == null) {
            this.id = record.internalGetId();
        }
        if (this.id.getAbilityDbid() == null) {
            this.id.setAbilityDbid(record.internalGetId().getAbilityDbid());
        }
        if (this.id.getSpeciesDbid() == null) {
            this.id.setSpeciesDbid(record.internalGetId().getSpeciesDbid());
        }
        if (this.hidden == null) {
            this.hidden = record.getHidden();
        }
    }

    public SpeciesAbilityKey internalGetId() {
        return id;
    }

    public void setId(SpeciesAbilityKey id)
    {
        this.id = id;
    }

    public Species secretGetSpecies() {
        return species;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesAbility)) return false;
        SpeciesAbility that = (SpeciesAbility) o;
        return Objects.equals(species.getDbid(), that.secretGetSpecies().getDbid()) &&
                Objects.equals(getAbility().getDbid(), that.getAbility().getDbid());
    }

}
