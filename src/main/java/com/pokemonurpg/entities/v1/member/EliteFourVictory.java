package com.pokemonurpg.entities.v1.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.entities.v1.gym.KnownEliteFourMember;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "elite_four_victory")
@Getter
@Setter
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
    private KnownEliteFourMember defender;

    @Column
    private Date date;

    @Column(name = "log_url")
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
}
