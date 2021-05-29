package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.gym.models.KnownChampion;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ChampionVictoryInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = KnownChampion.class)
    private String defender;

    private Date date;

    private String logUrl;

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }
}
