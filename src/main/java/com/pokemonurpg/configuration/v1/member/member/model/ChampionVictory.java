package com.pokemonurpg.configuration.v1.member.member.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.knownchampion.model.KnownChampion;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.ChampionVictoryInputDto;
import com.pokemonurpg.member.models.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "champion_victory")
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
    @JsonView(value = { MemberViews.Full.class })
    private KnownChampion defender;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Date date;

    @Column(name = "log_url")
    @JsonView(value = { MemberViews.Full.class })
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

    public Member getChallenger() {
        return challenger;
    }

    public void setChallenger(Member challenger) {
        this.challenger = challenger;
    }

    public KnownChampion getDefender() {
        return defender;
    }

    public void setDefender(KnownChampion defender) {
        this.defender = defender;
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
