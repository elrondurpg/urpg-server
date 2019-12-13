package com.pokemonurpg.dto.stats;

import com.pokemonurpg.object.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class OwnedPokemonDto {

    private int dbid;
    private int dexno;
    private String name;
    private String nickname;
    private String displayName;
    private String formName;
    private String gender;
    private String type1;
    private String type2;
    private int exp;
    private String obtained;
    private String obtainedLink;
    private String hiddenPowerType;
    private String hiddenPowerLink;
    private String nature;
    private List<String> abilities;
    private List<String> attacks;
    private Collection<EarnedRibbonDto> ribbons;

    public OwnedPokemonDto() {
    }

    public OwnedPokemonDto(OwnedPokemon ownedPokemon) {
        if (ownedPokemon != null) {
            setDbid(ownedPokemon.getDbid());
            setNickname(ownedPokemon.getNickname());
            setGender(ownedPokemon.getGender());
            setExp(ownedPokemon.getExp());

            if (ownedPokemon.getObtained() != null) {
                setObtained(ownedPokemon.getObtained().getName());
            }

            setObtainedLink(ownedPokemon.getObtainedLink());

            if (ownedPokemon.getNature() != null) {
                setNature(ownedPokemon.getNature().getName());
            }

            if (ownedPokemon.getHiddenPowerType() != null) {
                setHiddenPowerType(ownedPokemon.getHiddenPowerType().getName());
            }

            setHiddenPowerLink(ownedPokemon.getHiddenPowerLink());

            abilities = new ArrayList<>();
            List<OwnedHiddenAbility> ownedHiddenAbilities = ownedPokemon.getOwnedHiddenAbilities();
            for (OwnedHiddenAbility ability : ownedHiddenAbilities) {
                if (ability.getAbility() != null) {
                    abilities.add(ability.getAbility().getName());
                }
            }

            attacks = new ArrayList<>();
            List<OwnedExtraMove> ownedExtraMoves = ownedPokemon.getOwnedExtraMoves();
            for (OwnedExtraMove attack : ownedExtraMoves) {
                if (attack.getAttack() != null) {
                    attacks.add(attack.getAttack().getName());
                }
            }

            ribbons = new ArrayList<>();
            List<EarnedRibbon> earnedRibbons = ownedPokemon.getEarnedRibbons();

            HashMap<String, EarnedRibbonDto> ribbonRecords = new HashMap<>();

            for (EarnedRibbon ribbon : earnedRibbons) {
                EarnedRibbonDto earnedRibbonDto = new EarnedRibbonDto(ribbon);

                if (earnedRibbonDto.getAttribute() != null && earnedRibbonDto.getRank() != null) {
                    if (!ribbonRecords.containsKey(earnedRibbonDto.getAttribute() + earnedRibbonDto.getRank())) {
                        ribbonRecords.put(earnedRibbonDto.getAttribute() + earnedRibbonDto.getRank(), earnedRibbonDto);
                    }

                    EarnedRibbonDto record = ribbonRecords.get(earnedRibbonDto.getAttribute() + earnedRibbonDto.getRank());
                    record.setQuantity(record.getQuantity() + 1);
                    record.addLink(ribbon.getUrl());
                }
            }

            ribbons = ribbonRecords.values();

            Species species = ownedPokemon.getSpecies();
            if (species != null) {
                setDexno(species.getDexno());
                setName(species.getName());
                setDisplayName(species.getDisplayName());
                setFormName(species.getFormName());

                if (species.getType1() != null) {
                    setType1(species.getType1().getName());
                }
                if (species.getType2() != null) {
                    setType2(species.getType2().getName());
                }
            }
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getObtained() {
        return obtained;
    }

    public void setObtained(String obtained) {
        this.obtained = obtained;
    }

    public String getObtainedLink() {
        return obtainedLink;
    }

    public void setObtainedLink(String obtainedLink) {
        this.obtainedLink = obtainedLink;
    }

    public String getHiddenPowerType() {
        return hiddenPowerType;
    }

    public void setHiddenPowerType(String hiddenPowerType) {
        this.hiddenPowerType = hiddenPowerType;
    }

    public String getHiddenPowerLink() {
        return hiddenPowerLink;
    }

    public void setHiddenPowerLink(String hiddenPowerLink) {
        this.hiddenPowerLink = hiddenPowerLink;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    public List<String> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<String> attacks) {
        this.attacks = attacks;
    }

    public Collection<EarnedRibbonDto> getRibbons() {
        return ribbons;
    }

    public void setRibbons(Collection<EarnedRibbonDto> ribbons) {
        this.ribbons = ribbons;
    }
}
