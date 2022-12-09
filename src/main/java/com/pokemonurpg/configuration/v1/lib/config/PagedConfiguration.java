package com.pokemonurpg.configuration.v1.lib.config;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v2.shared.view.ListResponse;
import com.pokemonurpg.configuration.v2.shared.view.ListView;

@JsonSerialize(using = PagedConfigurationSerializer.class)
public class PagedConfiguration<T> {

    @JsonUnwrapped
    ListView<T> page;
    Class<T> view;

    public PagedConfiguration(Page<? extends T> page, Class<T> view) {
        this.page = new ListResponse<T>(page);
        this.view = view;
    }

    public ListView<T> getPage() {
        return page;
    }

    public Class<T> getView() {
        return view;
    }
}
