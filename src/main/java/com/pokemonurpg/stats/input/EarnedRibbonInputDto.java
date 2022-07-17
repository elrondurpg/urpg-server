package com.pokemonurpg.stats.input;

import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.ContestRank;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.stats.models.EarnedRibbon;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EarnedRibbonInputDto extends ChildInputDto {

    @ExistsInDb(type = EarnedRibbon.class)
    private Integer dbid;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = ContestRank.class)
    private String rank;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = ContestAttribute.class)
    private String attribute;

    @NotNull(groups = { ObjectCreation.class })
    @ExistsInDb(type = ContestType.class)
    private String contestType;

    @Pattern(regexp = "^((https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+(&page=\\d+)?|^(https://)?forum\\.pokemonurpg\\.com/showthread\\.php\\?tid=\\d+&pid=\\d+#pid\\d+|(https://)?pokemonurpg\\.com/archive/([a-z0-9\\-]+\\.\\d+/)*[a-z0-9\\-]+\\.\\d+(-page-\\d+)?\\.html|(https://)?pokemonurpg\\.com/archive/pxr/(\\d+-[A-Za-z0-9\\-()!]+/)+(page\\d+\\.html)?)$")
    @Size(max = 2083)
    private String url;

    private Boolean spent;

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSpent() {
        return spent;
    }

    public void setSpent(Boolean spent) {
        this.spent = spent;
    }

    public String getContestType() {
        return contestType;
    }

    public void setContestType(String contestType) {
        this.contestType = contestType;
    }

    
}
