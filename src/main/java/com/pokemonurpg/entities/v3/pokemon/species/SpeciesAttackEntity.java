package com.pokemonurpg.entities.v3.pokemon.species;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesSpeciesAttackView;
import com.pokemonurpg.entities.v3.attack.AttackEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "species_attack")
@Getter
@Setter
public class SpeciesAttackEntity implements GetSpeciesSpeciesAttackView {

    @EmbeddedId
    SpeciesAttackKeyEntity id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private SpeciesEntity species;

    @ManyToOne
    @MapsId("attack_dbid")
    @JoinColumn(name = "attack_dbid")
    private AttackEntity attack;

    @Column
    private String method = "LEVEL-UP";

    @Column
    private Integer generation;
}
