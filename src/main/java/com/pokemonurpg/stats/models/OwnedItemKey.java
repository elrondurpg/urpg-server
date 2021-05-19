package com.pokemonurpg.stats.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnedItemKey implements Serializable {

    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "item_dbid")
    private Integer itemDbid;

    public OwnedItemKey() {
    }

    public OwnedItemKey(int trainerDbid, int itemDbid) {
        this.trainerDbid = trainerDbid;
        this.itemDbid = itemDbid;
    }

    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public Integer getItemDbid() {
        return itemDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedItemKey that = (OwnedItemKey) o;
        return Objects.equals(trainerDbid, that.trainerDbid) &&
                Objects.equals(itemDbid, that.itemDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerDbid, itemDbid);
    }
}
