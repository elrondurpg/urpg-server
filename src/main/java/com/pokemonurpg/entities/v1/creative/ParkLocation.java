package com.pokemonurpg.entities.v1.creative;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.shared.view.GetSpeciesParkLocationView;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

@Entity
@Table(name="park_location")
public class ParkLocation extends NamedEntity implements GetSpeciesParkLocationView {
}
