package com.pokemonurpg.species.input;


import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.*;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpeciesInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    private Integer dexno;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 21)
    private String name;

    @Size(min = 3, max = 20)
    private String displayName;

    @Size(min = 3, max = 20)
    private String formName;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Type.class)
    @Pattern(regexp = "^(?!NONE).*$", message = "Type 1 must not be NONE.")
    private String type1;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Type.class)
    private String type2;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String classification;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer hp;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer attack;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer defense;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer specialAttack;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer specialDefense;

    @NotNull(groups = { ObjectCreation.class })
    @Min(1)
    @Max(1000)
    private Integer speed;

    @NotNull(groups = { ObjectCreation.class })
    @DecimalMin("0.01")
    @DecimalMax("1000000")
    private Double height;

    @NotNull(groups = { ObjectCreation.class })
    @DecimalMin("0.01")
    @DecimalMax("1000000")
    private Double weight;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean maleAllowed;

    @NotNull(groups = { ObjectCreation.class })
    private Boolean femaleAllowed;

    @Min(0)
    @Max(20000)
    private Integer pokemart;

    @Min(0)
    @Max(100000)
    private Integer contestCredits;

    @ExistsInDb(type = StoryRank.class)
    private String storyRank;

    @ExistsInDb(type = ArtRank.class)
    private String artRank;

    @ExistsInDb(type = ParkRank.class)
    private String parkRank;

    @ExistsInDb(type = ParkLocation.class)
    private String parkLocation;

    private List<@Valid SpeciesAttackInputDto> attacks = new ArrayList<>();

    private List<@Valid SpeciesAbilityInputDto> abilities = new ArrayList<>();

    private List<@Valid CosmeticFormInputDto> cosmeticForms = new ArrayList<>();

    @Size(min = 0, max = 100)
    private String alteredFormMethod;

    @ExistsInDb(type = Species.class)
    private String preEvolution;

    @Size(min = 3, max = 50)
    private String evolutionMethod;

    @Min(5)
    @Max(7)
    private Integer evolutionExpRequirement;

    @ExistsInDb(type = Species.class)
    private String preMega;

    @Size(min = 3, max = 20)
    private String megaStone;

    @Size(min = 3, max = 7)
    private String megaSuffix;

    @Min(0)
    @Max(2)
    private Integer legendaryTier;

    public Integer getDexno() {
        return dexno;
    }

    public void setDexno(Integer dexno) {
        this.dexno = dexno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getMaleAllowed() {
        return maleAllowed;
    }

    public void setMaleAllowed(Boolean maleAllowed) {
        this.maleAllowed = maleAllowed;
    }

    public Boolean getFemaleAllowed() {
        return femaleAllowed;
    }

    public void setFemaleAllowed(Boolean femaleAllowed) {
        this.femaleAllowed = femaleAllowed;
    }

    public Integer getPokemart() {
        return pokemart;
    }

    public void setPokemart(Integer pokemart) {
        this.pokemart = pokemart;
    }

    public Integer getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(Integer contestCredits) {
        this.contestCredits = contestCredits;
    }

    public String getStoryRank() {
        return storyRank;
    }

    public void setStoryRank(String storyRank) {
        this.storyRank = storyRank;
    }

    public String getArtRank() {
        return artRank;
    }

    public void setArtRank(String artRank) {
        this.artRank = artRank;
    }

    public String getParkRank() {
        return parkRank;
    }

    public void setParkRank(String parkRank) {
        this.parkRank = parkRank;
    }

    public String getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(String parkLocation) {
        this.parkLocation = parkLocation;
    }

    public List<SpeciesAttackInputDto> getAttacks() {
        if (attacks == null) return Collections.emptyList();
        else return attacks;
    }

    public void setAttacks(List<SpeciesAttackInputDto> attacks) {
        this.attacks = attacks;
    }

    public List<SpeciesAbilityInputDto> getAbilities() {
        if (abilities == null) return Collections.emptyList();
        else return abilities;
    }

    public void setAbilities(List<SpeciesAbilityInputDto> abilities) {
        this.abilities = abilities;
    }

    public List<CosmeticFormInputDto> getCosmeticForms() {
        if (cosmeticForms == null) return Collections.emptyList();
        else return cosmeticForms;
    }

    public void setCosmeticForms(List<CosmeticFormInputDto> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }

    public String getAlteredFormMethod() {
        return alteredFormMethod;
    }

    public void setAlteredFormMethod(String alteredFormMethod) {
        this.alteredFormMethod = alteredFormMethod;
    }

    public String getPreMega() {
        return preMega;
    }

    public void setPreMega(String preMega) {
        this.preMega = preMega;
    }

    public Integer getLegendaryTier() {
        return legendaryTier;
    }

    public void setLegendaryTier(Integer legendaryTier) {
        this.legendaryTier = legendaryTier;
    }

    public String getPreEvolution() {
        return preEvolution;
    }

    public void setPreEvolution(String preEvolution) {
        this.preEvolution = preEvolution;
    }

    public String getEvolutionMethod() {
        return evolutionMethod;
    }

    public void setEvolutionMethod(String evolutionMethod) {
        this.evolutionMethod = evolutionMethod;
    }

    public Integer getEvolutionExpRequirement() {
        return evolutionExpRequirement;
    }

    public void setEvolutionExpRequirement(Integer evolutionExpRequirement) {
        this.evolutionExpRequirement = evolutionExpRequirement;
    }

    public String getMegaStone() {
        return megaStone;
    }

    public void setMegaStone(String megaStone) {
        this.megaStone = megaStone;
    }

    public String getMegaSuffix() {
        return megaSuffix;
    }

    public void setMegaSuffix(String megaSuffix) {
        this.megaSuffix = megaSuffix;
    }
}
