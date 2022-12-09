package com.pokemonurpg.entities.v1.contest;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.GetSpeciesContestAttributeView;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Table(name = "contest_attribute")
@Entity
public class ContestAttribute extends NamedEntity implements GetSpeciesContestAttributeView {
}