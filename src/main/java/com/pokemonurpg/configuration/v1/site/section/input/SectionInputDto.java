package com.pokemonurpg.configuration.v1.site.section.input;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.site.section.model.Section;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Section.class)
@Getter
@Setter
public class SectionInputDto extends NamedConfigurationInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 15)
    private String name;

    @Min(0)
    private Integer tier1LegendaryRequirement;

    @Min(0)
    private Integer tier2LegendaryRequirement;
}
