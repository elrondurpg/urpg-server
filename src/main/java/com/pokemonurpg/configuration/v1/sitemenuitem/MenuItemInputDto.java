package com.pokemonurpg.configuration.v1.sitemenuitem;

import com.pokemonurpg.lib.validation.ObjectCreation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MenuItemInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 64)
    private String name;

    @Size(max = 2083)
    private String url;

    @Min(0) @Max(1)
    private Integer indent;

    @Min(0) @Max(100)
    private Integer position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIndent() {
        return indent;
    }

    public void setIndent(Integer indent) {
        this.indent = indent;
    }
}
