package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesSpeciesAttackView;
import com.pokemonurpg.entities.v1.attack.Attack;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "species_attack")
@Getter
@Setter
public class SpeciesAttack implements GetSpeciesSpeciesAttackView {

    @EmbeddedId
    SpeciesAttackKey id;

    @ManyToOne
    @MapsId("species_dbid")
    @JoinColumn(name = "species_dbid")
    private Species species;

    @ManyToOne
    @MapsId("attack_dbid")
    @JoinColumn(name = "attack_dbid")
    private Attack attack;

    @Column
    private String method = "LEVEL-UP";

    @Column
    private Integer generation;

    public SpeciesAttack(SpeciesAttackInputDto input, Species species, Attack attack) {
        this.update(input);
        this.id = SpeciesAttackKey.builder()
            .speciesDbid(species.getDbid())
            .attackDbid(attack.getDbid())
            .build();
        this.attack = attack;
        this.species = species;
    }

    public void update(SpeciesAttackInputDto input) {
        setMethod(input.getMethod());
        setGeneration(input.getGeneration());
    }
}
