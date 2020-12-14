package com.pokemonurpg.stats.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EarnedBadgeKey implements Serializable {
    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "gym_dbid")
    private Integer gymDbid;

    public EarnedBadgeKey() {
    }

    public EarnedBadgeKey(Integer trainerDbid, Integer gymDbid) {
        this.trainerDbid = trainerDbid;
        this.gymDbid = gymDbid;
    }
    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public Integer getGymDbid() {
        return gymDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EarnedBadgeKey that = (EarnedBadgeKey) o;
        return Objects.equals(trainerDbid, that.trainerDbid) &&
                Objects.equals(gymDbid, that.gymDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerDbid, gymDbid);
    }
}
