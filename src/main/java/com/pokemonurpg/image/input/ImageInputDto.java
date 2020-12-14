package com.pokemonurpg.image.input;

import com.pokemonurpg.image.models.ImageFolder;
import com.pokemonurpg.core.security.dto.AuthenticatedInputDto;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ImageInputDto extends AuthenticatedInputDto {
    @NotNull
    @Size(min = 1, max = 2083)
    private String url;

    @NotNull
    @Size(min = 1, max = 50)
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
