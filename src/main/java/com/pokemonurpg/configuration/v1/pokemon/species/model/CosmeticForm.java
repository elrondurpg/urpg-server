package com.pokemonurpg.configuration.v1.pokemon.species.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.view.ConfigurationViews;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Pokemon.class, ConfigurationViews.V1.Pokemon.Species.Full.class })
public class CosmeticForm {
    @Id
    @Column
    private String name;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    public CosmeticForm() {}

    public CosmeticForm(CosmeticFormInputDto input, Integer speciesDbid) {
        this.update(input);
        setName(input.getName());
        setSpeciesDbid(speciesDbid);
    }

    public void update(CosmeticFormInputDto input) {
        setFormName(input.getFormName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        if (formName != null) {
            this.formName = formName;
        }
    }

    public Integer getSpeciesDbid() {
        return speciesDbid;
    }

    public void setSpeciesDbid(Integer speciesDbid) {
        this.speciesDbid = speciesDbid;
    }
}
