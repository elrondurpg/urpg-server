package com.pokemonurpg.configuration.v1.elitefourmemberslots;

import com.pokemonurpg.lib.v1.request.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.OwnedPokemon;

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
