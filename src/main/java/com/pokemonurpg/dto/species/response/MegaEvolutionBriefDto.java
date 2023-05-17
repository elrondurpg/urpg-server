package com.pokemonurpg.dto.species.response;

public class MegaEvolutionBriefDto {
    private String name;
    private String megaStone;

    public MegaEvolutionBriefDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMegaStone() {
        return megaStone;
    }

    public void setMegaStone(String megaStone) {
        this.megaStone = megaStone;
    }
}
