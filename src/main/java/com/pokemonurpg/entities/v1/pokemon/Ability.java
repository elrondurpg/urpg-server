package com.pokemonurpg.entities.v1.pokemon;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesAbilityView;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ability")
public class Ability extends NamedEntity implements GetSpeciesAbilityView {

    @Column
    private String description;

    @OneToMany(mappedBy = "ability")
    private Set<SpeciesAbility> pokemon;
}
