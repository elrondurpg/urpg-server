package com.pokemonurpg.dto.battle;

import java.util.List;

public class BattlePokemonDto {
    private String nickname;
    private String species;
    private Character gender;
    private String ability;
    private String item;
    private List<String> attacks;

    public BattlePokemonDto() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<String> attacks) {
        this.attacks = attacks;
    }
}
