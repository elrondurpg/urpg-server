package com.pokemonurpg.general.input;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ValueConformsToType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValueConformsToType
public class FlagInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @Size(min = 3, max = 120)
    private String description;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "BOOLEAN|STRING|DOUBLE|INTEGER")
    private String type;

    @Size(max = 60)
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
