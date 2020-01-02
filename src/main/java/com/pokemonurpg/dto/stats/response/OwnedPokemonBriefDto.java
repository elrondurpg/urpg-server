package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.trainer.OwnedPokemon;
import com.pokemonurpg.object.pokemon.Species;

public class OwnedPokemonBriefDto {
    private int dbid;
    private int dexno;
    private String name;
    private String nickname;
    private String displayName;
    private String type1;
    private String type2;
    private String gender;

    public OwnedPokemonBriefDto() {
    }

    public OwnedPokemonBriefDto(OwnedPokemon pokemon) {
        if (pokemon != null) {
            setDbid(pokemon.getDbid());
            setNickname(pokemon.getNickname());
            setGender(pokemon.getGender());

            Species species = pokemon.getSpecies();
            if (species != null) {
                setDexno(species.getDexno());
                setName(species.getName());
                setDisplayName(species.getDisplayName());
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
