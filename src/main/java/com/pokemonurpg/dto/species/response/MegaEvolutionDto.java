package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.Type;

import java.util.List;

public class MegaEvolutionDto {
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
    private String displayName;
    private String formName;
    private SpeciesAbilityDto ability;
    private String megaStone;
    private List<TypeMatchupDto> typeMatchups;

    public MegaEvolutionDto(Species species, String megaStone) {
        if (species != null) {
            setDbid(species.getDbid());
            setDexno(species.getDexno());
            setName(species.getName());
            if (species.getType1() != null) {
                setType1(species.getType1().getName());
            }
            if (species.getType2() != null) {
                setType2(species.getType2().getName());
            }
            setClassification(species.getClassification());
            setHp(species.getHp());
            setAttack(species.getAttack());
            setDefense(species.getDefense());
            setSpecialAttack(species.getSpecialAttack());
            setSpecialDefense(species.getSpecialDefense());
            setSpeed(species.getSpeed());
            setHeight(species.getHeight());
            setWeight(species.getWeight());
            setMaleAllowed(species.getMaleAllowed());
            setFemaleAllowed(species.getFemaleAllowed());
            setDisplayName(species.getDisplayName());
            setFormName(species.getFormName());
            setMegaStone(megaStone);
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

    public SpeciesAbilityDto getAbility() {
        return ability;
    }

    public void setAbility(SpeciesAbilityDto ability) {
        this.ability = ability;
    }

    public String getMegaStone() {
        return megaStone;
    }

    public void setMegaStone(String megaStone) {
        this.megaStone = megaStone;
    }

    public List<TypeMatchupDto> getTypeMatchups() {
        return typeMatchups;
    }

    public void setTypeMatchups(List<TypeMatchupDto> typeMatchups) {
        this.typeMatchups = typeMatchups;
    }
}
