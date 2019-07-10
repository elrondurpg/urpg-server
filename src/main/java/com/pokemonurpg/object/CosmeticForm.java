package com.pokemonurpg.object;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

//@Entity
public class CosmeticForm implements Serializable {
    /*
    @EmbeddedId
    CosmeticFormKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "base_dbid")
    private Species species;

    @Column(name = "display_name")
    private String displayName;

    @Column
    private String method;

    public CosmeticForm() {}

    public CosmeticForm(int speciesDbid, String name, String displayName, String method) {
        this.id = new CosmeticFormKey(speciesDbid, name);
        this.displayName = displayName;
        this.method = method;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getName() {
        return id.getName();
    }

    public void setName(String name) {
        this.id.setName(name);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CosmeticForm)) return false;
        CosmeticForm that = (CosmeticForm) o;
        return Objects.equals(species.getDbid(), that.getSpecies().getDbid()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDisplayName(), that.getDisplayName()) &&
                Objects.equals(getMethod(), that.getMethod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecies().getDbid(), getName(), getDisplayName(), getMethod());
    }*/

}
