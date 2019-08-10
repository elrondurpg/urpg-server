package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;
import com.pokemonurpg.dto.species.response.CosmeticFormDto;

import java.util.List;

public class SpeciesInputDto extends InputDto {
    private Integer dexno;
    private String name;
    private String type1;
    private String type2;
    private String classification;
    private Integer hp;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Double height;
    private Double weight;
    private Boolean maleAllowed;
    private Boolean femaleAllowed;
    private Integer pokemart;
    private String storyRank;
    private String artRank;
    private String parkRank;
    private String parkLocation;
    private Integer contestCredits;
    private String displayName;
    private String formName;
    private List<SpeciesAttackInputDto> speciesAttacks;
    private List<SpeciesAbilityInputDto> speciesAbilities;
    private List<CosmeticFormDto> cosmeticForms;
    private String alteredFormMethod;
    private EvolutionInputDto evolvesFrom;
    private MegaEvolutionInputDto megaEvolvesFrom;

    public SpeciesInputDto() {
    }

    public int getDexno() {
        return dexno;
    }

    public void setDexno(int dexno) {
        this.dexno = dexno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isMaleAllowed() {
        return maleAllowed;
    }

    public void setMaleAllowed(boolean maleAllowed) {
        this.maleAllowed = maleAllowed;
    }

    public boolean isFemaleAllowed() {
        return femaleAllowed;
    }

    public void setFemaleAllowed(boolean femaleAllowed) {
        this.femaleAllowed = femaleAllowed;
    }

    public Integer getPokemart() {
        return pokemart;
    }

    public void setPokemart(Integer pokemart) {
        this.pokemart = pokemart;
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

    public Integer getContestCredits() {
        return contestCredits;
    }

    public void setContestCredits(Integer contestCredits) {
        this.contestCredits = contestCredits;
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

    public List<SpeciesAttackInputDto> getSpeciesAttacks() {
        return speciesAttacks;
    }

    public void setSpeciesAttacks(List<SpeciesAttackInputDto> speciesAttacks) {
        this.speciesAttacks = speciesAttacks;
    }

    public List<SpeciesAbilityInputDto> getSpeciesAbilities() {
        return speciesAbilities;
    }

    public void setSpeciesAbilities(List<SpeciesAbilityInputDto> speciesAbilities) {
        this.speciesAbilities = speciesAbilities;
    }

    public List<CosmeticFormDto> getCosmeticForms() {
        return cosmeticForms;
    }

    public void setCosmeticForms(List<CosmeticFormDto> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }

    public String getAlteredFormMethod() {
        return alteredFormMethod;
    }

    public void setAlteredFormMethod(String alteredFormMethod) {
        this.alteredFormMethod = alteredFormMethod;
    }

    public EvolutionInputDto getEvolvesFrom() {
        return evolvesFrom;
    }

    public void setEvolvesFrom(EvolutionInputDto evolvesFrom) {
        this.evolvesFrom = evolvesFrom;
    }

    public MegaEvolutionInputDto getMegaEvolvesFrom() {
        return megaEvolvesFrom;
    }

    public void setMegaEvolvesFrom(MegaEvolutionInputDto megaEvolvesFrom) {
        this.megaEvolvesFrom = megaEvolvesFrom;
    }
}
