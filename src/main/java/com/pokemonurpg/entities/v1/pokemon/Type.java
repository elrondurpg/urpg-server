package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.ListSpeciesTypeView;
import com.pokemonurpg.configuration.v2.attack.attack.shared.view.AttackResponseTypePart;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Entity
@Table(name = "type")
public class Type extends NamedEntity implements ListSpeciesTypeView, AttackResponseTypePart {
}
