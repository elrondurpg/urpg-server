package com.pokemonurpg.configuration.v1.site.flag.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.configuration.v1.lib.model.NamedConfigurationModel;
import com.pokemonurpg.configuration.v1.site.flag.FlagViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonView(value = { FlagViews.Brief.class })
public class Flag extends NamedConfigurationModel {

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String value;

    @JsonIgnore
    public boolean isTrue() {
        return "TRUE".equalsIgnoreCase(value);
    }
}
