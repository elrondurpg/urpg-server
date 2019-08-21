package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.*;

import java.util.List;

public class SpeciesDto {
    private int dbid;
    private int dexno;
    private String name;
    private String type1;
    private String type2;
    private String classification;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private double height;
    private double weight;
    private boolean maleAllowed;
    private boolean femaleAllowed;
    private Integer pokemart;
    private StoryRank storyRank;
    private ArtRank artRank;
    private ParkRank parkRank;
    private ParkLocation parkLocation;
    private Integer contestCredits;
    private String displayName;
    private String formName;
    private List<SpeciesAttackDto> speciesAttacks;
    private List<SpeciesAbilityDto> speciesAbilities;
    private List<CosmeticFormDto> cosmeticForms;
    private SpeciesPageTabDto nextSpecies;
    private SpeciesPageTabDto prevSpecies;
    private List<AlteredFormDto> alteredForms;
    private List<String> uniqueMoves;
    private String alteredFormMethod;
    private List<List<EvolutionFamilyMemberDto>> evolutionFamily;
    private List<MegaEvolutionDto> megaEvolutions;
    private List<TypeMatchupDto> typeMatchups;

    public SpeciesDto() {

    }

    public SpeciesDto(Species species) {
        if (species != null) {
            dbid = species.getDbid();
            dexno = species.getDexno();
            name = species.getName();
            type1 = species.getType1().getName();
            type2 = species.getType2().getName();
            classification = species.getClassification();
            hp = species.getHp();
            attack = species.getAttack();
            defense = species.getDefense();
            specialAttack = species.getSpecialAttack();
            specialDefense = species.getSpecialDefense();
            speed = species.getSpeed();
            height = species.getHeight();
            weight = species.getWeight();
            maleAllowed = species.getMaleAllowed();
            femaleAllowed = species.getFemaleAllowed();
            pokemart = species.getPokemart();
            storyRank = species.getStoryRank();
            parkRank = species.getParkRank();
            parkLocation = species.getParkLocation();
            artRank = species.getArtRank();
            contestCredits = species.getContestCredits();
            displayName = species.getDisplayName();
            formName = species.getFormName();
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
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

    public StoryRank getStoryRank() {
        return storyRank;
    }

    public void setStoryRank(StoryRank storyRank) {
        this.storyRank = storyRank;
    }

    public ArtRank getArtRank() {
        return artRank;
    }

    public void setArtRank(ArtRank artRank) {
        this.artRank = artRank;
    }

    public ParkRank getParkRank() {
        return parkRank;
    }

    public void setParkRank(ParkRank parkRank) {
        this.parkRank = parkRank;
    }

    public ParkLocation getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(ParkLocation parkLocation) {
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

    public List<SpeciesAttackDto> getSpeciesAttacks() {
        return speciesAttacks;
    }

    public void setSpeciesAttacks(List<SpeciesAttackDto> speciesAttacks) {
        this.speciesAttacks = speciesAttacks;
    }

    public List<SpeciesAbilityDto> getSpeciesAbilities() {
        return speciesAbilities;
    }

    public void setSpeciesAbilities(List<SpeciesAbilityDto> speciesAbilities) {
        this.speciesAbilities = speciesAbilities;
    }

    public List<CosmeticFormDto> getCosmeticForms() {
        return cosmeticForms;
    }

    public void setCosmeticForms(List<CosmeticFormDto> cosmeticForms) {
        this.cosmeticForms = cosmeticForms;
    }

    public SpeciesPageTabDto getNextSpecies() {
        return nextSpecies;
    }

    public void setNextSpecies(SpeciesPageTabDto nextSpecies) {
        this.nextSpecies = nextSpecies;
    }

    public SpeciesPageTabDto getPrevSpecies() {
        return prevSpecies;
    }

    public void setPrevSpecies(SpeciesPageTabDto prevSpecies) {
        this.prevSpecies = prevSpecies;
    }

    public List<AlteredFormDto> getAlteredForms() {
        return alteredForms;
    }

    public void setAlteredForms(List<AlteredFormDto> alteredForms) {
        this.alteredForms = alteredForms;
    }

    public List<String> getUniqueMoves() {
        return uniqueMoves;
    }

    public void setUniqueMoves(List<String> uniqueMoves) {
        this.uniqueMoves = uniqueMoves;
    }

    public String getAlteredFormMethod() {
        return alteredFormMethod;
    }

    public void setAlteredFormMethod(String alteredFormMethod) {
        this.alteredFormMethod = alteredFormMethod;
    }

    public List<List<EvolutionFamilyMemberDto>> getEvolutionFamily() {
        return evolutionFamily;
    }

    public void setEvolutionFamily(List<List<EvolutionFamilyMemberDto>> evolutionFamily) {
        this.evolutionFamily = evolutionFamily;
    }

    public List<MegaEvolutionDto> getMegaEvolutions() {
        return megaEvolutions;
    }

    public void setMegaEvolutions(List<MegaEvolutionDto> megaEvolutions) {
        this.megaEvolutions = megaEvolutions;
    }

    public List<TypeMatchupDto> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupDto> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }
}
