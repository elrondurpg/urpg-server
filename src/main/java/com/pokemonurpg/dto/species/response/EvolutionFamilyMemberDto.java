package com.pokemonurpg.dto.species.response;

import com.pokemonurpg.object.Species;

public class EvolutionFamilyMemberDto {
    private int dbid;
    private String name;
    private String displayName;
    private String method;

    public EvolutionFamilyMemberDto() { }

    public EvolutionFamilyMemberDto(Species species, String method) {
        if (species != null) {
            setDbid(species.getDbid());
            setName(species.getName());
            setDisplayName(species.getDisplayName());
            setMethod(method);
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
