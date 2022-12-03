package com.pokemonurpg.entities.v3.attack;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesAttackCategoryView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

@Entity
@Table(name = "attack_category")
public class AttackCategoryEntity extends NamedEntity implements GetSpeciesAttackCategoryView {

}
