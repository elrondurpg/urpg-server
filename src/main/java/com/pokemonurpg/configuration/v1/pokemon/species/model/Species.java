package com.pokemonurpg.configuration.v1.pokemon.species.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.ConfigurationViews;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.pokemon.ability.AbilityViews;
import com.pokemonurpg.configuration.v1.pokemon.species.SpeciesViews;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.type.model.Type;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;

import javax.persistence.*;

import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@JsonView(value = { SpeciesViews.Full.class })
public class Species extends ConfigurationModel implements NamedObject {

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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        if (height != null) {
            this.height = height;
        }
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        if (weight != null) {
            this.weight = weight;
        }
    }

    public Boolean getMaleAllowed() {
        return maleAllowed;
    }

    public void setMaleAllowed(Boolean maleAllowed) {
        if (maleAllowed != null) {
            this.maleAllowed = maleAllowed;
        }
    }

    public Boolean getFemaleAllowed() {
        return femaleAllowed;
    }

    public void setFemaleAllowed(Boolean femaleAllowed) {
        if (femaleAllowed != null) {
            this.femaleAllowed = femaleAllowed;
        }
    }

    public Integer getPokemart() {
        return pokemart;
    }

    public void setPokemart(Integer pokemart) {
        if (pokemart != null) {
            this.pokemart = pokemart;
        }
    }

    public StoryRank getStoryRank() {
        return storyRank;
    }

    public void setStoryRank(StoryRank storyRank) {
        if (storyRank != null) {
            this.storyRank = storyRank;
        }
    }

    public ArtRank getArtRank() {
        return artRank;
    }

    public void setArtRank(ArtRank artRank) {
        if (artRank != null) {
            this.artRank = artRank;
        }
    }

    public ParkLocation getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(ParkLocation parkLocation) {
        if (parkLocation != null) {
            this.parkLocation = parkLocation;
        }
    }

    public ParkRank getParkRank() {
        return parkRank;
    }

    public void setParkRank(ParkRank parkRank) {
        if (parkRank != null) {
            this.parkRank = parkRank;
        }
    }

    public Integer getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(Integer contestCredits) {
        if (contestCredits != null) {
            this.contestCredits = contestCredits;
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        if (displayName != null) {
            this.displayName = displayName;
        }
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        if (formName != null) {
            this.formName = formName;
        }
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        if (hp != null) {
            this.hp = hp;
        }
    }

    public Integer getDexno() {
        return dexno;
    }

    public void setDexno(Integer dexno) {
        if (dexno != null) {
            this.dexno = dexno;
        }
    }

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        if (type1 != null) {
            this.type1 = type1;
        }
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
        if (type2 != null) {
            this.type2 = type2;
        }
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        if (classification != null) {
            this.classification = classification;
        }
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        if (attack != null) {
            this.attack = attack;
        }
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        if (defense != null) {
            this.defense = defense;
        }
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        if (specialAttack != null) {
            this.specialAttack = specialAttack;
        }
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        if (specialDefense != null) {
            this.specialDefense = specialDefense;
        }
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        if (speed != null) {
            this.speed = speed;
        }
    }

    public List<SpeciesAttack> getAttacks() {
        return attacks;
    }

    public List<SpeciesAbility> getAbilities() {
        return abilities;
    }

    public Integer getLegendaryTier() {
        return legendaryTier;
    }

    public void setLegendaryTier(Integer legendaryTier) {
        if (legendaryTier != null) {
            this.legendaryTier = legendaryTier;
        }
    }

    public String getAlteredFormMethod() {
        return alteredFormMethod;
    }

    public void setAlteredFormMethod(String alteredFormMethod) {
        if (alteredFormMethod != null) {
            this.alteredFormMethod = alteredFormMethod;
        }
    }

    public Set<CosmeticForm> getCosmeticForms() {
        return cosmeticForms;
    }

    public Species getPreEvolution() {
        return preEvolution;
    }

    public void setPreEvolution(Species preEvolution) {
        if (preEvolution != null) {
            this.preEvolution = preEvolution;
        }
    }

    public String getEvolutionMethod() {
        return evolutionMethod;
    }

    public void setEvolutionMethod(String evolutionMethod) {
        if (evolutionMethod != null) {
            this.evolutionMethod = evolutionMethod;
        }
    }

    public Integer getEvolutionExpRequirement() {
        return evolutionExpRequirement;
    }

    public void setEvolutionExpRequirement(Integer evolutionExpRequirement) {
        if (evolutionExpRequirement != null) {
            this.evolutionExpRequirement = evolutionExpRequirement;
        }
    }

    public Species getPreMega() {
        return preMega;
    }

    public void setPreMega(Species preMega) {
        if (preMega != null) {
            this.preMega = preMega;
        }
    }

    public String getMegaStone() {
        return megaStone;
    }

    public void setMegaStone(String megaStone) {
        if (megaStone != null) {
            this.megaStone = megaStone;
        }
    }

    public String getMegaSuffix() {
        return megaSuffix;
    }

    public void setMegaSuffix(String megaSuffix) {
        if (megaSuffix != null) {
            this.megaSuffix = megaSuffix;
        }
    }

    public void setAttacks(List<SpeciesAttack> attacks) {
        this.attacks = attacks;
    }

    public void setAbilities(List<SpeciesAbility> abilities) {
        this.abilities = abilities;
    }

    public void setCosmeticForms(Set<CosmeticForm> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }

    public Boolean isBattleOnly() {
        return battleOnly;
    }

    public void setBattleOnly(Boolean battleOnly) {
        if (battleOnly != null) {
            this.battleOnly = battleOnly;
        }
    }

    public Boolean isFullyEvolved() {
        return fullyEvolved;
    }

    public void setFullyEvolved(Boolean fullyEvolved) {
        this.fullyEvolved = fullyEvolved;
    }

    public Boolean isEvolved() {
        return evolved;
    }

    public void setEvolved(Boolean evolved) {
        this.evolved = evolved;
    }

    public Boolean isMegaEvolved() {
        return megaEvolved;
    }

    public void setMegaEvolved(Boolean megaEvolved) {
        this.megaEvolved = megaEvolved;
    }

    
    
}
