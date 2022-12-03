package com.pokemonurpg.entities.v3.pokemon.species;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseSpeciesAbilityView;
import com.pokemonurpg.entities.v3.pokemon.ability.AbilityEntity;

@Entity
@Table(name = "species_ability")
@Getter
@Setter
public class SpeciesAbilityEntity implements SpeciesFullResponseSpeciesAbilityView {

    @EmbeddedId
    SpeciesAbilityKeyEntity id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private SpeciesEntity species;

    @ManyToOne
    @MapsId("ability_dbid")
    @JoinColumn(name = "ability_dbid")
    private AbilityEntity ability;

    @Column(name = "is_hidden")
    private Boolean hidden = false;

}

