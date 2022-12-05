package com.pokemonurpg.entities.v1.pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesPreEvolutionView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesPreMegaView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.GetSpeciesView;
import com.pokemonurpg.configuration.v3.pokemon.species.shared.view.ListSpeciesView;
import com.pokemonurpg.entities.v1.creative.ArtRank;
import com.pokemonurpg.entities.v1.creative.ParkLocation;
import com.pokemonurpg.entities.v1.creative.ParkRank;
import com.pokemonurpg.entities.v1.creative.StoryRank;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "species")
public class Species extends NamedEntity 
    implements ListSpeciesView, GetSpeciesView, GetSpeciesPreEvolutionView,
        GetSpeciesPreMegaView {
    @Column
    private Integer dexno;

    @OneToOne
    @JoinColumn(name = "type1_dbid")
    private Type type1;

    @OneToOne
    @JoinColumn(name = "type2_dbid")
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
    private Species preEvolution;

    @Column(name="evolution_method")
    private String evolutionMethod;

    @Column(name="evolution_exp_requirement")
    private Integer evolutionExpRequirement;

    @OneToOne
    @JoinColumn(name = "pre_mega_dbid")
    private Species preMega;

    @Column(name="mega_stone")
    private String megaStone;

    @Column(name="mega_suffix")
    private String megaSuffix;

    @Column(name="battle_only")
    private Boolean battleOnly;

    // This field exists to be used in search queries using Spring's Example class.
    @Formula("(select (count(*) = 0) from Species s2 where dbid = s2.pre_evolution_dbid)")
    private Boolean fullyEvolved;
    
    // This field exists to be used in search queries using Spring's Example class.
    @Formula(value = "(pre_evolution_dbid is not null)")
    private Boolean evolved;
    
    // This field exists to be used in search queries using Spring's Example class.
    @Formula(value = "(pre_mega_dbid is not null)")
    private Boolean megaEvolved;

    /*public void setDefaultValues() {
        if (getDisplayName() == null) setDisplayName(getName());
        if (getBattleOnly() == null) setBattleOnly(SpeciesConstants.DEFAULT_BATTLE_ONLY);
        if (getLegendaryTier() == null) setLegendaryTier(SpeciesConstants.DEFAULT_LEGENDARY_TIER);
        if (getFemaleAllowed() == null) setFemaleAllowed(SpeciesConstants.DEFAULT_FEMALE_ALLOWED);
        if (getMaleAllowed() == null) setMaleAllowed(SpeciesConstants.DEFAULT_MALE_ALLOWED);
    }*/
}