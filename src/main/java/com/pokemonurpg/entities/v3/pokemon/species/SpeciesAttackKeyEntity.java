package com.pokemonurpg.entities.v3.pokemon.species;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class SpeciesAttackKeyEntity implements Serializable {

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    @Column(name = "attack_dbid")
    private Integer attackDbid;
}
