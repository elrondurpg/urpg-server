package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.Type;

import java.util.HashMap;
import java.util.List;

public class AlteredFormDto {
    private int dbid;
    private String name;
    private String formName;
    private String displayName;
    private Type type1;
    private Type type2;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private List<String> uniqueAbilities;
    private HashMap<String, String> uniqueAttacks;

    public AlteredFormDto(Species species) {
        if (species != null) {
            dbid = species.getDbid();
            name = species.getName();
            type1 = species.getType1();
            type2 = species.getType2();
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

    public Type getType1() {
        return type1;
    }

    public void setType1(Type type1) {
        this.type1 = type1;
    }

    public Type getType2() {
        return type2;
    }

    public void setType2(Type type2) {
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

    public List<String> getUniqueAbilities() {
        return uniqueAbilities;
    }

    public void setUniqueAbilities(List<String> uniqueAbilities) {
        this.uniqueAbilities = uniqueAbilities;
    }

    public HashMap<String, String> getUniqueAttacks() {
        return uniqueAttacks;
    }

    public void setUniqueAttacks(HashMap<String, String> uniqueAttacks) {
        this.uniqueAttacks = uniqueAttacks;
    }
}
