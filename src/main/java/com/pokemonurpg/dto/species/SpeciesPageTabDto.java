package com.pokemonurpg.dto.species;

import com.pokemonurpg.object.Species;

public class SpeciesPageTabDto {
    private int dexno;
    private String name;

    public SpeciesPageTabDto(Species species) {
        if (species != null) {
            dexno = species.getDexno();
            name = species.getDisplayName();
        }
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
}
