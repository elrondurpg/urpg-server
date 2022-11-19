package com.pokemonurpg.configuration.v1.creative.parklocation.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.creative.parklocation.ParkLocationViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import javax.persistence.*;

@Entity
@JsonView(value = { ParkLocationViews.Brief.class, SpeciesViews.Full.class })
public class ParkLocation extends NamedConfigurationModel {
}
