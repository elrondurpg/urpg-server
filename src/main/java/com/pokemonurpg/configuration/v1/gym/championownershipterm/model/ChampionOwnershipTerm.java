package com.pokemonurpg.configuration.v1.gym.championownershipterm.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.ChampionOwnershipTermViews;
import com.pokemonurpg.configuration.v1.lib.model.IndexedConfigurationModel;
import com.pokemonurpg.gym.models.Champion;
import com.pokemonurpg.member.models.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { ChampionOwnershipTermViews.Brief.class })
public class ChampionOwnershipTerm extends IndexedConfigurationModel {

    @OneToOne
    @JoinColumn(name = "slot_dbid")
    @JsonView(value = { ChampionOwnershipTermViews.Id.class })
    private Champion slot;

    @OneToOne
    @JoinColumn(name = "owner_dbid")
    @JsonView(value = { ChampionOwnershipTermViews.Id.class })
    private Member owner;

    @Column(name = "open_date")
    @JsonView(value = { ChampionOwnershipTermViews.Id.class })
    private Date openDate;

    @Column
    private Integer wins;

    @Column
    private Integer losses;

    @Column
    private Integer draws;
}
