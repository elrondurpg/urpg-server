package com.pokemonurpg.configuration.v1.member.member.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.model.KnownEliteFourMember;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.member.models.Member;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "elite_four_victory")
public class EliteFourVictory {

    @EmbeddedId
    EliteFourVictoryKey id;

    @ManyToOne
    @MapsId("challenger_dbid")
    @JoinColumn(name="challenger_dbid", nullable=false)
    private Member challenger;

    @ManyToOne
    @MapsId("defender_dbid")
    @JoinColumn(name="defender_dbid")
    @JsonView(value = { MemberViews.Full.class })
    private KnownEliteFourMember defender;

    @Column
    @JsonView(value = { MemberViews.Full.class })
    private Date date;

    @Column(name = "log_url")
    @JsonView(value = { MemberViews.Full.class })
    private String logUrl;

    public EliteFourVictory() {}

    public EliteFourVictory(EliteFourVictoryInputDto input, Member challenger, KnownEliteFourMember defender) {
        this.update(input);
        this.id = new EliteFourVictoryKey(challenger.getDbid(), defender.getDbid());
        setChallenger(challenger);
        setDefender(defender);
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

    public KnownEliteFourMember getDefender() {
        return defender;
    }

    public void setDefender(KnownEliteFourMember defender) {
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
