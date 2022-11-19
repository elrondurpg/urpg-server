package com.pokemonurpg.configuration.v1.site.section.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.site.section.SectionViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { SectionViews.Brief.class })
public class Section extends NamedConfigurationModel {

    @Column(name = "tier1_legendary_requirement")
    private Integer tier1LegendaryRequirement;

    @Column(name = "tier2_legendary_requirement")
    private Integer tier2LegendaryRequirement;
}
