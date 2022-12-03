package com.pokemonurpg.entities.v3.pokemon.species;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.SpeciesFullResponseCosmeticFormView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CosmeticFormEntity implements SpeciesFullResponseCosmeticFormView {
    @Id
    @Column
    private String name;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "species_dbid")
    private Integer speciesDbid;
}
