package com.pokemonurpg.configuration.v1.lib.config;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pokemonurpg.configuration.v1.lib.view.ConfigurationViews;

@JsonSerialize(using = ConfigurationPageSerializer.class)
public class ConfigurationPageMapper {

    Page<?> page;
    Class<? extends ConfigurationViews.V1> view;

    public ConfigurationPageMapper(Page<?> page, Class<? extends ConfigurationViews.V1> view) {
        this.page = page;
        this.view = view;
    }

    public Page<?> getPage() {
        return page;
    }

    public Class<? extends ConfigurationViews.V1> getView() {
        return view;
    }
}
