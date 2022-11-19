package com.pokemonurpg.configuration.v1.gym.knowngymleader.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.KnownGymLeaderViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Entity
@JsonView(value = { KnownGymLeaderViews.Id.class })
public class KnownGymLeader extends NamedConfigurationModel {
}
