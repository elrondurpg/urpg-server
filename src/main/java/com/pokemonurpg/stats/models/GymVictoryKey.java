package com.pokemonurpg.stats.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GymVictoryKey implements Serializable {

    @Column(name = "challenger_dbid")
    private Integer challengerDbid;

    @Column
    private String defender;

    @Column(name = "gym_dbid")
    private Integer gymDbid;

    @Column(name = "league_dbid")
    private Integer leagueDbid;

    public GymVictoryKey() {
    }

    public GymVictoryKey(int challengerDbid, String defender, int gymDbid, int leagueDbid) {
        this.challengerDbid = challengerDbid;
        this.defender = defender;
        this.gymDbid = gymDbid;
        this.leagueDbid = leagueDbid;
    }

    public Integer getChallengerDbid() {
        return challengerDbid;
    }

    public String getDefender() {
        return defender;
    }

    public Integer getGymDbid() {
        return gymDbid;
    }

    public Integer getLeagueDbid() {
        return leagueDbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymVictoryKey that = (GymVictoryKey) o;
        return Objects.equals(challengerDbid, that.challengerDbid) &&
                Objects.equals(defender, that.defender) &&
                Objects.equals(gymDbid, that.gymDbid) &&
                Objects.equals(leagueDbid, that.leagueDbid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(challengerDbid, defender, gymDbid, leagueDbid);
    }
}
