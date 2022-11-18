package com.pokemonurpg.configuration.v1.contest.type.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.contest.type.ContestTypeViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Table(name = "contest_type")
@Entity
@JsonView(value = { ContestTypeViews.Id.class })
public class ContestType extends NamedConfigurationModel {

}
