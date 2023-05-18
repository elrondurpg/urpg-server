package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.lib.v1.requests.ChildRequest;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedRequest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CosmeticFormRequest extends ChildRequest implements UniquelyNamedRequest {
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
