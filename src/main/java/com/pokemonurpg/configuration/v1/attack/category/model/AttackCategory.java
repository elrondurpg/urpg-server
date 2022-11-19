package com.pokemonurpg.configuration.v1.attack.category.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.attack.category.AttackCategoryViews;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.attack.attack.AttackViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;

import javax.persistence.*;

@Entity
@Table(name = "attack_category")
@JsonView(value = { AttackCategoryViews.Id.class, AttackViews.Brief.class, SpeciesViews.Full.class })
public class AttackCategory extends NamedConfigurationModel {

}
