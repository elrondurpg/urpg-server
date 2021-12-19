package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.GymVictoryInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gym_victory")
@JsonView(value = { View.MemberView.Summary.class })
public class GymVictory {

    @EmbeddedId
    GymVictoryKey id;

    @ManyToOne
    @MapsId("challenger_dbid")
    @JoinColumn(name="challenger_dbid")
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories", "gymVictories",
            "championTerms", "eliteFourTerms" })
    private Member challenger;

    @ManyToOne
    @MapsId("defender_dbid")
    @JoinColumn(name="defender_dbid")
    @JsonIgnoreProperties({"dbid"})
    private KnownGymLeader defender;

    @ManyToOne
    @MapsId("gym_dbid")
    @JoinColumn(name="gym_dbid")
    @JsonIgnoreProperties({"dbid", "badge", "type", "victories", "pokemon" })
    private Gym gym;

    @ManyToOne
    @MapsId("league_dbid")
    @JoinColumn(name="league_dbid")
    @JsonIgnoreProperties({ "dbid", "gyms" })
    private GymLeague league;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public GymVictory() {
    }

    public GymVictory(GymVictoryInputDto input, Member challenger, KnownGymLeader defender, Gym gym, GymLeague league) {
        this.update(input);
        this.id = new GymVictoryKey(challenger.getDbid(), defender.getDbid(), gym.getDbid(), league.getDbid());
        setChallenger(challenger);
        setDefender(defender);
        setGym(gym);
        setLeague(league);
    }

    public void update(GymVictoryInputDto input) {
        setDate(input.getDate());
        setLogUrl(input.getLogUrl());
    }

    public Member getChallenger() {
        return challenger;
    }

    public void setChallenger(Member challenger) {
        this.challenger = challenger;
    }

    public KnownGymLeader getDefender() {
        return defender;
    }

    public void setDefender(KnownGymLeader defender) {
        this.defender = defender;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public GymLeague getLeague() {
        return league;
    }

    public void setLeague(GymLeague league) {
        this.league = league;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }
}
