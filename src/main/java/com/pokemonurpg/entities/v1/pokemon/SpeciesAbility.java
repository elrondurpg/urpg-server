package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesSpeciesAbilityView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "species_ability")
@Getter
@Setter
public class SpeciesAbility implements GetSpeciesSpeciesAbilityView {

    @EmbeddedId
    SpeciesAbilityKey id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private Species species;

    @ManyToOne
    @MapsId("ability_dbid")
    @JoinColumn(name = "ability_dbid")
    private Ability ability;

    @Column(name = "is_hidden")
    private Boolean hidden = false;
    
    public SpeciesAbility(SpeciesAbilityInputDto input, Species species, Ability ability) {
        this.update(input);
        this.id = SpeciesAbilityKey.builder()
            .speciesDbid(species.getDbid())
            .abilityDbid(ability.getDbid())
            .build();
        this.ability = ability;
        this.species = species;
    }

    public void update(SpeciesAbilityInputDto input) {
        setHidden(input.getHidden());
    }
}

