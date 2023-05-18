package com.pokemonurpg.configuration.v1.sections;


import com.pokemonurpg.entities.v1.Section;
import com.pokemonurpg.lib.v1.request.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UniqueName(type = Section.class)
public class SectionInputDto implements UniquelyNamedInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 15)
    private String name;

    @Min(0)
    private Integer tier1LegendaryRequirement;

    @Min(0)
    private Integer tier2LegendaryRequirement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTier1LegendaryRequirement() {
        return tier1LegendaryRequirement;
    }

    public void setTier1LegendaryRequirement(Integer tier1LegendaryRequirement) {
        this.tier1LegendaryRequirement = tier1LegendaryRequirement;
    }

    public Integer getTier2LegendaryRequirement() {
        return tier2LegendaryRequirement;
    }

    public void setTier2LegendaryRequirement(Integer tier2LegendaryRequirement) {
        this.tier2LegendaryRequirement = tier2LegendaryRequirement;
    }
}
