package com.pokemonurpg.entities.v3.contest;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesContestAttributeView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

@Table(name = "contest_attribute")
@Entity
public class ContestAttributeEntity extends NamedEntity implements GetSpeciesContestAttributeView {
}