package com.pokemonurpg.gym.input;


import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.gym.annotation.AllPokemonBelongToOwner;
import com.pokemonurpg.gym.annotation.BelongsToThisGym;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.species.models.Type;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class GymInputDto {

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 20)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = Badge.class)
    private String badge;

    @ExistsInDb(type = Type.class)
    private String type;

    @AllPokemonBelongToOwner
    private List<@Valid GymPokemonInputDto> pokemon = new ArrayList<>();

    @BelongsToThisGym
    private Integer currentOwnerRecordDbid;

    private Boolean removeOwner = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GymPokemonInputDto> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<GymPokemonInputDto> pokemon) {
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
