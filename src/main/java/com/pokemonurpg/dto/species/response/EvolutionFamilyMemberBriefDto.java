package com.pokemonurpg.dto.species.response;

public class EvolutionFamilyMemberBriefDto {
    private String name;
    private String method;

    public EvolutionFamilyMemberBriefDto() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
