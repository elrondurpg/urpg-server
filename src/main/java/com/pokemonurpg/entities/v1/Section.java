package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.sections.SectionInputDto;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Section implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column(name = "tier1_legendary_requirement")
    private Integer tier1LegendaryRequirement;

    @Column(name = "tier2_legendary_requirement")
    private Integer tier2LegendaryRequirement;

    public Section() {}

    public Section(SectionInputDto input) {
        this.update(input);
    }

    public void update(SectionInputDto input) {
        setName(input.getName());
        setTier1LegendaryRequirement(input.getTier1LegendaryRequirement());
        setTier2LegendaryRequirement(input.getTier2LegendaryRequirement());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public Integer getTier1LegendaryRequirement() {
        return this.tier1LegendaryRequirement;
    }

    public void setTier1LegendaryRequirement(Integer tier1LegendaryRequirement) {
        if (tier1LegendaryRequirement != null) {
            this.tier1LegendaryRequirement = tier1LegendaryRequirement;
        }
    }

    public Integer getTier2LegendaryRequirement() {
        return this.tier2LegendaryRequirement;
    }

    public void setTier2LegendaryRequirement(Integer tier2LegendaryRequirement) {
        if (tier2LegendaryRequirement != null) {
            this.tier2LegendaryRequirement = tier2LegendaryRequirement;
        }
    }
}
