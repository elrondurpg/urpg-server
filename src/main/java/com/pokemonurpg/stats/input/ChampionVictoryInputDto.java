package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ChampionVictoryInputDto extends ChildInputDto {
    @NotNull
    @Size(min = 3, max = 30)
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
