package com.pokemonurpg.gym.input;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class KnownGymLeaderBulkInputDto {
    List<@Valid KnownGymLeaderInputDto> leaders = new ArrayList<>();

    public KnownGymLeaderBulkInputDto() {

    }

    public List<KnownGymLeaderInputDto> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<KnownGymLeaderInputDto> leaders) {
        this.leaders = leaders;
    }
}
