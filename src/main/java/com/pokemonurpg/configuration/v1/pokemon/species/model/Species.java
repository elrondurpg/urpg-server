package com.pokemonurpg.configuration.v1.pokemon.species.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.constants.SpeciesConstants;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@JsonView(value = { SpeciesViews.Full.class })
@Getter
@Setter
public class Species extends ConfigurationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonView(value = { SpeciesViews.Id.class, AbilityViews.Full.class })
    private Integer dbid;

    @Column
    @JsonView(value = { SpeciesViews.Brief.class })
    private Integer dexno;

    @Column
    @JsonView(value = { SpeciesViews.Id.class, AbilityViews.Full.class })
    private String name;

    @OneToOne
    @JoinColumn(name = "type1_dbid")
    @JsonView(value = { SpeciesViews.Brief.class })
    private Type type1;

    @OneToOne
    @JoinColumn(name = "type2_dbid")
    @JsonView(value = { SpeciesViews.Brief.class })
    private Type type2;

    @Column
    private String classification;

    @Column
    private Integer hp;

    @Column
    private Integer attack;

    @Column
    private Integer defense;

    @Column(name = "special_attack")
    private Integer specialAttack;

    @Column(name = "special_defense")
    private Integer specialDefense;

    @Column
    private Integer speed;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column(name = "male_allowed")
    private Boolean maleAllowed;

    @Column(name = "female_allowed")
    private Boolean femaleAllowed;

    @Column
    private Integer pokemart;

    @OneToOne
    @JoinColumn(name = "story_rank")
    private StoryRank storyRank;

    @OneToOne
    @JoinColumn(name = "art_rank")
    private ArtRank artRank;

    @OneToOne
    @JoinColumn(name = "park_location")
    private ParkLocation parkLocation;

    @OneToOne
    @JoinColumn(name = "park_rank")
    private ParkRank parkRank;

    @Column(name = "contest_credits")
    private Integer contestCredits;

    @Column(name = "display_name")
    @JsonView(value = { SpeciesViews.Brief.class })
    private String displayName;

    @Column(name = "form_name")
    private String formName;

    @OneToMany(mappedBy="species")
    private List<SpeciesAttack> attacks = new ArrayList<>();

    @OneToMany(mappedBy="species")
    private List<SpeciesAbility> abilities = new ArrayList<>();

    @Column(name="legendary_tier")
    private Integer legendaryTier;

    @Column(name="altered_form_method")
    private String alteredFormMethod;

    @OneToMany
    @JoinColumn(name="species_dbid")
    private Set<CosmeticForm> cosmeticForms;

    @OneToOne
    @JoinColumn(name = "pre_evolution_dbid")
    @JsonIgnoreProperties({ "abilities", "attacks", "classification", "hp", "attack", "defense", "specialAttack",
            "specialDefense", "speed", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "cosmeticForms",
            "alteredFormMethod", "type1", "type2", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    private Species preEvolution;

    @Column(name="evolution_method")
    private String evolutionMethod;

    @Column(name="evolution_exp_requirement")
    private Integer evolutionExpRequirement;

    @OneToOne
    @JoinColumn(name = "pre_mega_dbid")
    @JsonIgnoreProperties({ "abilities", "attacks", "classification", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "cosmeticForms",
            "alteredFormMethod", "type1", "type2", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix", "battleOnly"})
    private Species preMega;

    @Column(name="mega_stone")
    private String megaStone;

    @Column(name="mega_suffix")
    private String megaSuffix;

    @Column(name="battle_only")
    private Boolean battleOnly;

    // This field exists to be used in search queries using Spring's Example class.
    @Formula("(select (count(*) = 0) from Species s2 where dbid = s2.pre_evolution_dbid)")
    @JsonIgnore
    private Boolean fullyEvolved;
    
    // This field exists to be used in search queries using Spring's Example class.
    @Formula(value = "(pre_evolution_dbid is not null)")
    @JsonIgnore
    private Boolean evolved;
    
    // This field exists to be used in search queries using Spring's Example class.
    @Formula(value = "(pre_mega_dbid is not null)")
    @JsonIgnore
    private Boolean megaEvolved;

    public Species() {
    }

    public void setDefaultValues() {
        if (getDisplayName() == null) setDisplayName(getName());
        if (getBattleOnly() == null) setBattleOnly(SpeciesConstants.DEFAULT_BATTLE_ONLY);
        if (getLegendaryTier() == null) setLegendaryTier(SpeciesConstants.DEFAULT_LEGENDARY_TIER);
        if (getFemaleAllowed() == null) setFemaleAllowed(SpeciesConstants.DEFAULT_FEMALE_ALLOWED);
        if (getMaleAllowed() == null) setMaleAllowed(SpeciesConstants.DEFAULT_MALE_ALLOWED);
    }
}
