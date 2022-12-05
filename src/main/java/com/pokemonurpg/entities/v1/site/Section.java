package com.pokemonurpg.entities.v1.site;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Section extends NamedEntity {

    @Column(name = "tier1_legendary_requirement")
    private Integer tier1LegendaryRequirement;

    @Column(name = "tier2_legendary_requirement")
    private Integer tier2LegendaryRequirement;
}
