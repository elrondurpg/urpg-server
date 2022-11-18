package com.pokemonurpg.configuration.v1.contest.attribute.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.contest.attribute.ContestAttributeViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;

import javax.persistence.*;

@Table(name = "contest_attribute")
@Entity
@JsonView(value = { AttackViews.Full.class, ContestAttributeViews.Id.class })
public class ContestAttribute extends NamedConfigurationModel {
}