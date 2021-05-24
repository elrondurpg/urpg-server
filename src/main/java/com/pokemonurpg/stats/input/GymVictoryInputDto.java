package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class GymVictoryInputDto extends ChildInputDto {
    @NotNull
    @Size(min = 3, max = 30)
    private String defender;

    @NotNull
    @ExistsInDb(type = Gym.class)
    private String gym;

    @NotNull
    @ExistsInDb(type = GymLeague.class)
    private String league;

    private Date date;

    private String logUrl;

    public String getDefender() {
        return defender;
    }

    public void setDefender(String defender) {
        this.defender = defender;
    }

    public String getGym() {
        return gym;
    }

    public void setGym(String gym) {
        this.gym = gym;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
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
