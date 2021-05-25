package com.pokemonurpg.gym.input;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class KnownChampionBulkInputDto {
    List<@Valid KnownChampionInputDto> champions = new ArrayList<>();

    public KnownChampionBulkInputDto() {

    }

    public List<KnownChampionInputDto> getChampions() {
        return champions;
    }

    public void setChampions(List<KnownChampionInputDto> champions) {
        this.champions = champions;
    }
}
