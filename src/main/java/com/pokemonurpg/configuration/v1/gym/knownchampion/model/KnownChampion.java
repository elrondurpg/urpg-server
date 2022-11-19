package com.pokemonurpg.configuration.v1.gym.knownchampion.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.knownchampion.KnownChampionViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Entity
@JsonView(value = { KnownChampionViews.Id.class })
public class KnownChampion extends NamedConfigurationModel {
}
