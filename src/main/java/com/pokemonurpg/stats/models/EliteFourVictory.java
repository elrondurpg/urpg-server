package com.pokemonurpg.stats.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.EliteFourVictoryInputDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "elite_four_victory")
@JsonView(value = { View.MemberView.Summary.class })
public class EliteFourVictory {

    @EmbeddedId
    EliteFourVictoryKey id;

    @ManyToOne
    @MapsId("challenger_dbid")
    @JoinColumn(name="challenger_dbid", nullable=false)
    @JsonIgnoreProperties({"dbid", "discordId", "salt", "accessToken", "refreshToken", "sessionExpire",
            "money", "wins", "losses", "draws", "joinDate", "pokemon", "items",
            "badges", "championRecords", "legendaryProgress", "earnedLegendaries", "roles",
            "banned", "banExpiration", "gyms", "bot", "eliteFourVictories", "championVictories", "gymVictories" })
    private Member challenger;

    @Column(insertable = false, updatable = false)
    private String defender;

    @Column
    private Date date;

    @Column(name = "log_url")
    private String logUrl;

    public EliteFourVictory() {}

    public EliteFourVictory(EliteFourVictoryInputDto input, Member challenger) {
        this.update(input);
        this.id = new EliteFourVictoryKey(challenger.getDbid(), input.getDefender());
        setChallenger(challenger);
        setDefender(input.getDefender());
    }

    public void update(EliteFourVictoryInputDto input) {
        setDate(input.getDate());
        setLogUrl(input.getLogUrl());
    }

    public Member getChallenger() {
        return challenger;
    }

    public void setChallenger(Member challenger) {
        this.challenger = challenger;
    }

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date != null) {
            this.date = date;
        }
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        if (logUrl != null) {
            this.logUrl = logUrl;
        }
    }
}
