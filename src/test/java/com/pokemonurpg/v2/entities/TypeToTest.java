package com.pokemonurpg.v2.entities;

import com.pokemonurpg.v2.entities.pokemon.Type;

public class TypeToTest implements Type {
    public static final Integer DBID = 202505;
    public static final String NAME = "TypeToTest";

    private Integer dbid = DBID;
    private String name = NAME;

    public Integer getDbid() {
        return dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
