package com.pokemonurpg.configuration.v1.gym.badge.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.gym.badge.BadgeViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Entity
@JsonView(value = { BadgeViews.Id.class })
public class Badge extends NamedConfigurationModel {
}
