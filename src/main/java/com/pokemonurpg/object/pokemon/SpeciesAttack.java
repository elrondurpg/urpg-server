package com.pokemonurpg.object.pokemon;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "species_attack")
public class SpeciesAttack {

    @EmbeddedId
    SpeciesAttackKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private Species species;

    @ManyToOne
    @MapsId("attack_dbid")
    @JoinColumn(name = "attack_dbid")
    private Attack attack;

    @Column
    private String method;

    @Column
    private Integer generation;

    public SpeciesAttack() {    }

    public SpeciesAttack(int speciesDbid, int attackDbid, String method, Integer generation) {
        this.id = new SpeciesAttackKey(speciesDbid, attackDbid);
        this.method = method;
        this.generation = generation;
    }

    public void cloneValuesFrom(SpeciesAttack record) {
        if (id == null) {
            id = record.internalGetId();
        }
        if (id.getAttackDbid() == null) {
            id.setAttackDbid(record.internalGetId().getAttackDbid());
        }
        if (id.getSpeciesDbid() == null) {
            id.setSpeciesDbid(record.internalGetId().getSpeciesDbid());
        }
        if (method == null) {
            method = record.getMethod();
        }
    }

    public SpeciesAttackKey internalGetId() {
        return id;
    }

    public void setId(SpeciesAttackKey id)
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

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getGeneration() {
        return generation;
    }

    public void setGeneration(Integer generation) {
        this.generation = generation;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpeciesAttack)) return false;
        SpeciesAttack that = (SpeciesAttack) o;
        return Objects.equals(species.getDbid(), that.secretGetSpecies().getDbid()) &&
                Objects.equals(getAttack().getDbid(), that.getAttack().getDbid());
    }
}
