package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.ContestAttribute;
import com.pokemonurpg.entities.v1.ContestRank;
import com.pokemonurpg.entities.v1.ContestGeneration;
import com.pokemonurpg.lib.v1.requests.ChildRequest;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.lib.v1.strings.GeneralConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EarnedRibbonRequest extends ChildRequest {
    @NotNull
    @ExistsInDb(type = ContestRank.class)
    private String rank;

    @NotNull
    @ExistsInDb(type = ContestAttribute.class)
    private String attribute;

    @NotNull
    @ExistsInDb(type = ContestGeneration.class)
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

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
}
