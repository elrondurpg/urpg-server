package com.pokemonurpg.configuration.v1.championslots;

import com.pokemonurpg.lib.v1.requests.ChildInputDto;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.OwnedPokemon;

import javax.validation.constraints.NotNull;

public class ChampionPokemonInputDto extends ChildInputDto {
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
