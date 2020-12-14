package com.pokemonurpg.species.input;

import com.pokemonurpg.core.input.ChildInputDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CosmeticFormInputDto extends ChildInputDto {
    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 20)
    private String formName;

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
        this.formName = formName;
    }
}