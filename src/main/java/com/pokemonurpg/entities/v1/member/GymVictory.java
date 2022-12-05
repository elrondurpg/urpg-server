package com.pokemonurpg.entities.v1.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.entities.v1.gym.KnownGymLeader;
import com.pokemonurpg.entities.v1.gym.League;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gym_victory")
@Getter
@Setter
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
    private KnownGymLeader defender;

    @ManyToOne
    @MapsId("gym_dbid")
    @JoinColumn(name="gym_dbid")
    private Gym gym;

    @ManyToOne
    @MapsId("league_dbid")
    @JoinColumn(name="league_dbid")
    private League league;

    @Column
    private Date date;

    @Column(name = "log_url")
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
}
