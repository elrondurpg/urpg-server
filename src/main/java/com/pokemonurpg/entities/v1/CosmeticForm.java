package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.configuration.v1.pokemon.CosmeticFormRequest;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class CosmeticForm {
    @Id
    @Column
    private String name;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "species_dbid")
    private Integer speciesDbid;

    public CosmeticForm() {}

    public CosmeticForm(CosmeticFormRequest input, Integer speciesDbid) {
        this.update(input);
        setName(input.getName());
        setSpeciesDbid(speciesDbid);
    }

    public void update(CosmeticFormRequest input) {
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
