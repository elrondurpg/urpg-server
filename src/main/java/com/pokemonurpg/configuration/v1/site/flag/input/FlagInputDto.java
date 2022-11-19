package com.pokemonurpg.configuration.v1.site.flag.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.site.flag.model.Flag;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.core.validation.annotation.ValueConformsToType;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValueConformsToType
@UniqueName(type = Flag.class)
@Getter
@Setter
public class FlagInputDto extends NamedConfigurationInputDto {

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
}
