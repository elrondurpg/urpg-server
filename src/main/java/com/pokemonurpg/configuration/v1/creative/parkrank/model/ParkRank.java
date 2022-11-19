package com.pokemonurpg.configuration.v1.creative.parkrank.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.creative.parkrank.ParkRankViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { ParkRankViews.Brief.class, SpeciesViews.Full.class })
public class ParkRank extends NamedConfigurationModel {

    @Column
    private String requirement;
}
