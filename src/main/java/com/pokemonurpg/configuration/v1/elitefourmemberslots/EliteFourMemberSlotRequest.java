package com.pokemonurpg.configuration.v1.elitefourmemberslots;


import com.pokemonurpg.lib.v1.annotations.AllPokemonBelongToOwner;
import com.pokemonurpg.lib.v1.annotations.BelongsToThisEliteFourMemberSlot;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class EliteFourMemberSlotRequest {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @AllPokemonBelongToOwner
    private List<@Valid EliteFourMemberPokemonRequest> pokemon = new ArrayList<>();

    @BelongsToThisEliteFourMemberSlot
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EliteFourMemberPokemonRequest> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<EliteFourMemberPokemonRequest> pokemon) {
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
