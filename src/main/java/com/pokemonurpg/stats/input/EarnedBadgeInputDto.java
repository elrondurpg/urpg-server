package com.pokemonurpg.stats.input;

import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.gym.models.Gym;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

public class EarnedBadgeInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = Gym.class)
    private Integer gymDbid;

    private Date date;

    @Pattern(regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$")
    @Size(max = 2083)
    private String logUrl;

    public Integer getGymDbid() {
        return gymDbid;
    }

    public void setGymDbid(Integer gymDbid) {
        this.gymDbid = gymDbid;
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
