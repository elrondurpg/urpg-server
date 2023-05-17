package com.pokemonurpg.object;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CosmeticFormKey implements Serializable {

    @Column(name = "base_dbid")
    private int speciesDbid;

    @Column
    private String name;

    public CosmeticFormKey() {    }

    public CosmeticFormKey(Integer speciesDbid, String name) {
        this.speciesDbid = speciesDbid;
        this.name = name;
    }

    public int getSpeciesDbid() {
        return speciesDbid;
    }

    public void setSpeciesDbid(int speciesDbid) {
        this.speciesDbid = speciesDbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CosmeticFormKey)) return false;
        CosmeticFormKey that = (CosmeticFormKey) o;
        return Objects.equals(getSpeciesDbid(), that.getSpeciesDbid()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpeciesDbid(), getName());
    }
}
