package com.pokemonurpg.entities.v3.pokemon.type;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesBriefResponseTypeView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

@Entity
@Table(name = "type")
public class TypeEntity extends NamedEntity implements SpeciesBriefResponseTypeView {
}
