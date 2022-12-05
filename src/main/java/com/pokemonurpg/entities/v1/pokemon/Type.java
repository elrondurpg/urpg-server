package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesTypeView;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Entity
@Table(name = "type")
public class Type extends NamedEntity implements ListSpeciesTypeView {
}
