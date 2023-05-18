package com.pokemonurpg.configuration.v1.featureflags;

import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.ValueConformsToType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValueConformsToType
public class FeatureFlagRequest {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;

    @Size(min = 3, max = 120)
    private String description;

    @NotNull(groups = { ObjectCreation.class })
    @Pattern(regexp = "BOOLEAN|STRING|NUMBER")
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
