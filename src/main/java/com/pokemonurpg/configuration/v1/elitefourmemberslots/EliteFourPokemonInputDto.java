package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
import com.pokemonurpg.stats.models.OwnedPokemon;

import javax.validation.constraints.NotNull;

public class EliteFourPokemonInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = OwnedPokemon.class)
    private Integer dbid;

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }
}
