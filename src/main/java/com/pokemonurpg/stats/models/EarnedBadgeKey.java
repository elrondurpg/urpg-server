package com.pokemonurpg.stats.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EarnedBadgeKey implements Serializable {
    @Column(name = "trainer_dbid")
    private Integer trainerDbid;

    @Column(name = "type_dbid")
    private Integer typeDbid;

    @Column(name = "league_dbid")
    private Integer leagueDbid;

    @Column(name = "trainer_name")
    private String trainerName;

    public EarnedBadgeKey() {
    }

    public EarnedBadgeKey(Integer trainerDbid, Integer typeDbid, Integer leagueDbid, String trainerName) {
        this.trainerDbid = trainerDbid;
        //this.gymDbid = gymDbid;
    }

    public Integer getTrainerDbid() {
        return trainerDbid;
    }

    public Integer getTypeDbid() {
        return typeDbid;
    }

    public Integer getLeagueDbid() {
        return leagueDbid;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EarnedBadgeKey that = (EarnedBadgeKey) o;
        return Objects.equals(trainerDbid, that.trainerDbid) &&
                Objects.equals(typeDbid, that.typeDbid) &&
                Objects.equals(leagueDbid, that.leagueDbid) &&
                Objects.equals(trainerName, that.trainerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainerDbid, typeDbid, leagueDbid, trainerName);
    }
}
