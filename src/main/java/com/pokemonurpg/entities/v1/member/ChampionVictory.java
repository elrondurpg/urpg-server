package com.pokemonurpg.entities.v1.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.member.member.input.ChampionVictoryInputDto;
import com.pokemonurpg.entities.v1.gym.KnownChampion;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "champion_victory")
@Getter
@Setter
public class ChampionVictory {

    @EmbeddedId
    ChampionVictoryKey id;

    @ManyToOne
    @MapsId("challenger_dbid")
    @JoinColumn(name="challenger_dbid")
    private Member challenger;

    @ManyToOne
    @MapsId("defender_dbid")
    @JoinColumn(name="defender_dbid")
    private KnownChampion defender;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public ChampionVictory() {
    }

    public ChampionVictory(ChampionVictoryInputDto input, Member challenger, KnownChampion defender) {
        this.update(input);
        this.id = new ChampionVictoryKey(challenger.getDbid(), defender.getDbid());
        setChallenger(challenger);
        setDefender(defender);
    }

    public void update(ChampionVictoryInputDto input) {
        setDate(input.getDate());
        setLogUrl(input.getLogUrl());
    }
}
