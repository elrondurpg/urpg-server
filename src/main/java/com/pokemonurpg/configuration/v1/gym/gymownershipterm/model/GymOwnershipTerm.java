package com.pokemonurpg.configuration.v1.gym.gymownershipterm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.GymOwnershipTermViews;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.gym.gym.GymViews;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.gym.lib.constants.GymConstants;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.member.models.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { GymOwnershipTermViews.Brief.class })
public class GymOwnershipTerm extends IndexedConfigurationModel {

    @OneToOne
    @JoinColumn(name = "gym_dbid")
    @JsonView(value = { GymOwnershipTermViews.Id.class, MemberViews.Full.class })
    private Gym gym;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonView(value = { GymOwnershipTermViews.Id.class, GymViews.Brief.class })
    private Member owner;

    @OneToOne
    @JoinColumn(name = "league_dbid")
    @JsonView(value = { GymOwnershipTermViews.Brief.class, GymViews.Brief.class, MemberViews.Full.class })
    private League league;

    @Column(name = "open_date")
    @JsonView(value = { GymOwnershipTermViews.Id.class, GymViews.Brief.class, MemberViews.Full.class })
    private Date openDate;

    @Column
    @JsonView(value = { GymOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer wins;

    @Column
    @JsonView(value = { GymOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer losses;

    @Column
    @JsonView(value = { GymOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Integer draws;

    @OneToOne
    @JoinColumn(name = "tm_dbid")
    @JsonView(value = { GymOwnershipTermViews.Brief.class, MemberViews.Full.class })
    private Item tm;

    public void setDefaultValues() {
        if (getWins() == null) setWins(GymConstants.DEFAULT_WINS);
        if (getDraws() == null) setDraws(GymConstants.DEFAULT_LOSSES);
        if (getLosses() == null) setLosses(GymConstants.DEFAULT_DRAWS);
    }
}
