package com.pokemonurpg.entities.v1.pokemon;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesAbilityKey implements Serializable {

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    @Column(name = "ability_dbid")
    private Integer abilityDbid;

}
