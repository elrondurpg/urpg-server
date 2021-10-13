package com.pokemonurpg.species.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.species.models.CosmeticForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = CosmeticForm.class)
public class CosmeticFormInputDto extends ChildInputDto implements UniquelyNamedInputDto {
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
