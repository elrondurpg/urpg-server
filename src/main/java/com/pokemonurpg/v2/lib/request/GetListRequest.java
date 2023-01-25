package com.pokemonurpg.v2.lib.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public abstract class GetListRequest {
    private int itemsPerPage;
    private int page;
    private String sortBy;

    public void setSortBy(String sortBy) {
        if ("".equals(sortBy) || sortBy == null) {
            this.sortBy = null;
        }
        else {
            this.sortBy = sortBy.toLowerCase();
        }
    }
}
