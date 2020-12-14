package com.pokemonurpg.species.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.species.input.SpeciesInputDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer dbid;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer dexno;

    @Column
    @JsonView(value = { View.MemberView.Summary.class })
    private String name;

    @OneToOne
    @JoinColumn(name = "type1_dbid")
    @JsonView(value = { View.MemberView.Summary.class })
    private Type type1;

    @OneToOne
    @JoinColumn(name = "type2_dbid")
    @JsonView(value = { View.MemberView.Summary.class })
    private Type type2;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String classification;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer hp;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer attack;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer defense;

    @Column(name = "special_attack")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer specialAttack;

    @Column(name = "special_defense")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer specialDefense;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer speed;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Double height;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Double weight;

    @Column(name = "male_allowed")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Boolean maleAllowed = false;

    @Column(name = "female_allowed")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Boolean femaleAllowed = false;

    @Column
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer pokemart;

    @OneToOne
    @JoinColumn(name = "story_rank")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private StoryRank storyRank;

    @OneToOne
    @JoinColumn(name = "art_rank")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private ArtRank artRank;

    @OneToOne
    @JoinColumn(name = "park_location")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private ParkLocation parkLocation;

    @OneToOne
    @JoinColumn(name = "park_rank")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private ParkRank parkRank;

    @Column(name = "contest_credits")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer contestCredits;

    @Column(name = "display_name")
    @JsonView(value = { View.MemberView.Summary.class })
    private String displayName;

    @Column(name = "form_name")
    @JsonView(value = { View.MemberView.Summary.class })
    private String formName;

    @OneToMany(mappedBy="species")
    @JsonIgnoreProperties({ "species"})
    @JsonView(value = { View.MemberView.Pokemon.class })
    private List<SpeciesAttack> mappedSpeciesAttacks = new ArrayList<>();

    @OneToMany(mappedBy="species")
    @JsonIgnoreProperties({ "species"})
    @JsonView(value = { View.MemberView.Pokemon.class })
    private List<SpeciesAbility> mappedSpeciesAbilities = new ArrayList<>();

    @Column(name="legendary_tier")
    @JsonView(value = { View.MemberView.Summary.class })
    private Integer legendaryTier;

    @Column(name="altered_form_method")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String alteredFormMethod;

    @OneToMany
    @JoinColumn(name="species_dbid")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Set<CosmeticForm> cosmeticForms;

    @OneToOne
    @JoinColumn(name = "pre_evolution_dbid")
    @JsonIgnoreProperties({ "mappedSpeciesAbilities", "mappedSpeciesAttacks", "classification", "hp", "attack", "defense", "specialAttack",
            "specialDefense", "speed", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "mappedEvolution", "cosmeticForms",
            "alteredFormMethod", "type1", "type2", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix" })
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Species preEvolution;

    @Column(name="evolution_method")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String evolutionMethod;

    @Column(name="evolution_exp_requirement")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Integer evolutionExpRequirement;

    @OneToOne
    @JoinColumn(name = "pre_mega_dbid")
    @JsonIgnoreProperties({ "mappedSpeciesAbilities", "mappedSpeciesAttacks", "classification", "hp", "attack", "defense", "specialAttack",
            "specialDefense", "speed", "height", "weight", "maleAllowed", "femaleAllowed", "pokemart",
            "storyRank", "artRank", "parkRank", "parkLocation", "contestCredits", "legendaryTier", "mappedEvolution", "cosmeticForms",
            "alteredFormMethod", "type1", "type2", "formName", "preEvolution", "evolutionMethod", "evolutionExpRequirement",
            "preMega", "megaStone", "megaSuffix"})
    @JsonView(value = { View.MemberView.Pokemon.class })
    private Species preMega;

    @Column(name="mega_stone")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String megaStone;

    @Column(name="mega_suffix")
    @JsonView(value = { View.MemberView.Pokemon.class })
    private String megaSuffix;

    public Species() { }

    public Species(SpeciesInputDto input) {
        this.update(input);
    }

    public void update(SpeciesInputDto input) {
        setDexno(input.getDexno());
        setName(input.getName());
        setClassification(input.getClassification());
        setHp(input.getHp());
        setAttack(input.getAttack());
        setDefense(input.getDefense());
        setSpecialAttack(input.getSpecialAttack());
        setSpecialDefense(input.getSpecialDefense());
        setSpeed(input.getSpeed());
        setHeight(input.getHeight());
        setWeight(input.getWeight());
        setMaleAllowed(input.getMaleAllowed());
        setFemaleAllowed(input.getFemaleAllowed());
        setPokemart(input.getPokemart());
        setContestCredits(input.getContestCredits());
        setDisplayName(input.getDisplayName());
        setFormName(input.getFormName());
        setLegendaryTier(input.getLegendaryTier());
        setAlteredFormMethod(input.getAlteredFormMethod());
        setEvolutionMethod(input.getEvolutionMethod());
        setEvolutionExpRequirement(input.getEvolutionExpRequirement());
        setMegaStone(input.getMegaStone());
        setMegaSuffix(input.getMegaSuffix());
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

    public List<SpeciesAttack> getMappedSpeciesAttacks() {
        return mappedSpeciesAttacks;
    }

    public List<SpeciesAbility> getMappedSpeciesAbilities() {
        return mappedSpeciesAbilities;
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

    public void setMappedSpeciesAttacks(List<SpeciesAttack> mappedSpeciesAttacks) {
        this.mappedSpeciesAttacks = mappedSpeciesAttacks;
    }

    public void setMappedSpeciesAbilities(List<SpeciesAbility> mappedSpeciesAbilities) {
        this.mappedSpeciesAbilities = mappedSpeciesAbilities;
    }

    public void setCosmeticForms(Set<CosmeticForm> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }
}