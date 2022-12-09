package com.pokemonurpg.entities.v1.attack;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.GetSpeciesAttackCategoryView;
import com.pokemonurpg.configuration.v2.attack.attack.shared.view.AttackResponseCategoryPart;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Entity
@Table(name = "attack_category")
public class AttackCategory extends NamedEntity implements AttackResponseCategoryPart, GetSpeciesAttackCategoryView {

}
