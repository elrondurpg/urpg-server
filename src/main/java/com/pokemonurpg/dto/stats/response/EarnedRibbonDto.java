package com.pokemonurpg.dto.stats.response;

import com.pokemonurpg.object.trainer.EarnedRibbon;

import java.util.ArrayList;
import java.util.List;

public class EarnedRibbonDto {
    private String rank;
    private String attribute;
    private int quantity = 0;
    private List<String> links = new ArrayList<>();

    public EarnedRibbonDto() {
    }

    public EarnedRibbonDto(EarnedRibbon ribbon) {
        if (ribbon.getRank() != null) {
            setRank(ribbon.getRank().getName());
        }

        if (ribbon.getAttribute() != null) {
            setAttribute(ribbon.getAttribute().getName());
        }


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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public void addLink(String link) {
        links.add(link);
    }
}
