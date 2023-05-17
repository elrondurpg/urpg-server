package com.pokemonurpg.configuration.v1.elitefourmemberslots;


import com.pokemonurpg.lib.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.lib.annotations.BelongsToThisEliteFourSlot;
import com.pokemonurpg.lib.validation.ObjectCreation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EliteFourInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @AllPokemonBelongToOwner
    private List<@Valid EliteFourPokemonInputDto> pokemon = new ArrayList<>();

    @BelongsToThisEliteFourSlot
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EliteFourPokemonInputDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<EliteFourPokemonInputDto> pokemon) {
        this.pokemon = pokemon;
    }

    public Integer getCurrentOwnerRecordDbid() {
        return currentOwnerRecordDbid;
    }

    public void setCurrentOwnerRecordDbid(Integer currentOwnerRecordDbid) {
        this.currentOwnerRecordDbid = currentOwnerRecordDbid;
    }

    public Boolean getRemoveOwner() {
        return removeOwner;
    }

    public void setRemoveOwner(Boolean removeOwner) {
        this.removeOwner = removeOwner;
    }
}