package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.ChampionVictoryInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "champion_victory")
@JsonView(value = { View.MemberView.Summary.class })
public class ChampionVictory {

    @EmbeddedId
    ChampionVictoryKey id;

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
