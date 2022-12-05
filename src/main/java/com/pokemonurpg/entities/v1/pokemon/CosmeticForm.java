package com.pokemonurpg.entities.v1.pokemon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesCosmeticFormView;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cosmetic_form")
public class CosmeticForm implements GetSpeciesCosmeticFormView {
    @Id
    @Column
    private String name;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    public CosmeticForm(CosmeticFormInputDto input, Integer speciesDbid) {
        this.update(input);
        setName(input.getName());
        setSpeciesDbid(speciesDbid);
    }

    public void update(CosmeticFormInputDto input) {
        setFormName(input.getFormName());
    }
}
