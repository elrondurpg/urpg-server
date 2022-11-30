package com.pokemonurpg.configuration.v1.member.member.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.model.KnownGymLeader;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gym_victory")
public class GymVictory {

    @EmbeddedId
    GymVictoryKey id;

    @ManyToOne
    @MapsId("challenger_dbid")
    @JoinColumn(name="challenger_dbid")
    private Member challenger;

    @ManyToOne
    @MapsId("defender_dbid")
    @JoinColumn(name="defender_dbid")
    @JsonView(value = { MemberViews.Full.class })
    private KnownGymLeader defender;

    @ManyToOne
    @MapsId("gym_dbid")
    @JoinColumn(name="gym_dbid")
    @JsonView(value = { MemberViews.Full.class })
    private Gym gym;

    @ManyToOne
    @MapsId("league_dbid")
    @JoinColumn(name="league_dbid")
    @JsonView(value = { MemberViews.Full.class })
    private League league;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Date date;

    @Column(name = "log_url")
    @JsonView(value = { MemberViews.Full.class })
    private String logUrl;

    public GymVictory() {
    }

    public GymVictory(GymVictoryInputDto input, Member challenger, KnownGymLeader defender, Gym gym, League league) {
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
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
