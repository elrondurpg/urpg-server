package com.pokemonurpg.configuration.v1.images;

import com.pokemonurpg.entities.v1.ImageFolder;

import com.pokemonurpg.lib.v1.annotations.ExistsInDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ImageRequest {
    @NotNull
    @Size(min = 1, max = 2083)
    private String url;

    @NotNull
    @Size(min = 1, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9\\-_\\.]*$")
    private String name;

    @NotNull
    @ExistsInDb(type = ImageFolder.class)
    private String folder;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder.toLowerCase();
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
