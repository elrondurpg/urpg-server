package com.pokemonurpg.dto.species.input;

import com.pokemonurpg.dto.InputDto;

public class MegaEvolutionInputDto extends InputDto {
    private String name;
    private String megaStone;

    public MegaEvolutionInputDto() {
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
