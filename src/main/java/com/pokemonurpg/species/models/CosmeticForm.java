package com.pokemonurpg.species.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.species.input.CosmeticFormInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Pokemon.class })
public class CosmeticForm {
    @Id
    @Column
    private String name;

    @Column(name = "form_name")
    private String formName;

    public CosmeticForm() {}

    public CosmeticForm(CosmeticFormInputDto input) {
        this.update(input);
        setName(input.getName());
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
}
