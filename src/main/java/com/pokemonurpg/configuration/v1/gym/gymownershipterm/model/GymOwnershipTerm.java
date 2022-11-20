package com.pokemonurpg.configuration.v1.gym.gymownershipterm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.GymOwnershipTermViews;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
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
    @JsonView(value = { GymOwnershipTermViews.Id.class })
    private Gym gym;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonView(value = { GymOwnershipTermViews.Id.class })
    private Member owner;

    @OneToOne
    @JoinColumn(name = "league_dbid")
    private League league;

    @Column(name = "open_date")
    @JsonView(value = { GymOwnershipTermViews.Id.class })
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;

    @OneToOne
    @JoinColumn(name = "tm_dbid")
    private Item tm;

}
