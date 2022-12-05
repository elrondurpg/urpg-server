package com.pokemonurpg.stats.input;

import com.pokemonurpg.entities.v1.contest.ContestAttribute;
import com.pokemonurpg.entities.v1.contest.ContestRank;
import com.pokemonurpg.entities.v1.contest.ContestType;
import com.pokemonurpg.core.input.ChildInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.strings.GeneralConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EarnedRibbonInputDto extends ChildInputDto {
    @NotNull
    @ExistsInDb(type = ContestRank.class)
    private String rank;

    @NotNull
    @ExistsInDb(type = ContestAttribute.class)
    private String attribute;

    @NotNull
    @ExistsInDb(type = ContestType.class)
    private String generation;

    @NotNull
    @Pattern(regexp = GeneralConstants.ALLOWED_URL_PATTERN)
    @Size(max = 2083)
    private String logUrl;

    private Integer spent;

    private Integer quantity;

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

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    public Integer getSpent() {
        return spent;
    }

    public void setSpent(Integer spent) {
        this.spent = spent;
    }

    public String getGeneration() {
        return generation;
    }

    public void getGeneration(String generation) {
        this.generation = generation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
}
