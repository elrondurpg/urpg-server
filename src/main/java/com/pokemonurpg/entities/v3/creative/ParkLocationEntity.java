package com.pokemonurpg.entities.v3.creative;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesParkLocationView;
import com.pokemonurpg.entities.v3.shared.NamedEntity;

@Entity
@Table(name="park_location")
public class ParkLocationEntity extends NamedEntity implements GetSpeciesParkLocationView {
}
