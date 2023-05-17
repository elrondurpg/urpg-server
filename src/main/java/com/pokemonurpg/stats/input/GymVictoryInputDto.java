package com.pokemonurpg.stats.input;

import com.pokemonurpg.lib.input.ChildInputDto;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.entities.KnownGymLeader;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;

public class GymVictoryInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = KnownGymLeader.class)
    private String defender;

    @NotNull
    @ExistsInDb(type = Gym.class)
    private String gym;

    @NotNull
    @ExistsInDb(type = GymLeague.class)
    private String league;

    private Date date;

    @Size(max = 2083)
    @Pattern(
        regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$",
        message = "The provided log URL must come from forum.pokemonurpg.com or one of the URPG forum archives."
    )
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
