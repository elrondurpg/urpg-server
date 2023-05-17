package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.Species;

import java.util.HashMap;
import java.util.List;

public class AlteredFormDto {
    private int dbid;
    private String name;
    private String formName;
    private String baseName;
    private String displayName;
    private String type1;
    private String type2;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private List<SpeciesAbilityDto> abilities;
    private HashMap<String, String> uniqueAttacks;
    private String method;
    private boolean cosmetic = false;

    public AlteredFormDto(Species species) {
        if (species != null) {
            dbid = species.getDbid();
            name = species.getName();
            baseName = species.getName();
            if (species.getType1() != null) {
                setType1(species.getType1().getName());
            }
            if (species.getType2() != null) {
                setType2(species.getType2().getName());
            }
            hp = species.getHp();
            attack = species.getAttack();
            defense = species.getDefense();
            specialAttack = species.getSpecialAttack();
            specialDefense = species.getSpecialDefense();
            speed = species.getSpeed();
            displayName = species.getDisplayName();
            formName = species.getFormName();
        }
    }

    public AlteredFormDto(Species species, CosmeticFormDto cosmeticFormDto) {
        if (cosmeticFormDto != null) {
            setFormName(cosmeticFormDto.getFormName());
            setName(cosmeticFormDto.getName());
            setMethod(cosmeticFormDto.getMethod());
        }
        if (species != null) {
            setDbid(species.getDbid());
            if (species.getType1() != null) {
                setType1(species.getType1().getName());
            }
            if (species.getType2() != null) {
                setType2(species.getType2().getName());
            }
            setHp(species.getHp());
            setAttack(species.getAttack());
            setDefense(species.getDefense());
            setSpecialAttack(species.getSpecialAttack());
            setSpecialDefense(species.getSpecialDefense());
            setSpeed(species.getSpeed());
            setDisplayName(species.getDisplayName());
            setBaseName(species.getName());
        }
    }

    public AlteredFormDto(AlteredFormDto species, CosmeticFormDto cosmeticFormDto) {
        if (cosmeticFormDto != null) {
            setFormName(cosmeticFormDto.getFormName());
            setName(cosmeticFormDto.getName());
            setMethod(cosmeticFormDto.getMethod());
        }
        if (species != null) {
            setDbid(species.getDbid());
            setType1(species.getType1());
            setType2(species.getType2());
            setHp(species.getHp());
            setAttack(species.getAttack());
            setDefense(species.getDefense());
            setSpecialAttack(species.getSpecialAttack());
            setSpecialDefense(species.getSpecialDefense());
            setSpeed(species.getSpeed());
            setDisplayName(species.getDisplayName());
            setBaseName(species.getName());
        }
    }

    public int getDbid() {
        return dbid;
    }

    public void setDbid(int dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
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

    public List<SpeciesAbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<SpeciesAbilityDto> abilities) {
        this.abilities = abilities;
    }

    public HashMap<String, String> getUniqueAttacks() {
        return uniqueAttacks;
    }

    public void setUniqueAttacks(HashMap<String, String> uniqueAttacks) {
        this.uniqueAttacks = uniqueAttacks;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(boolean cosmetic) {
        this.cosmetic = cosmetic;
    }
}
