package com.pokemonurpg.configuration.v1.imagefolders;


import com.pokemonurpg.entities.v1.ImageFolder;
import com.pokemonurpg.lib.v1.requests.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.annotations.UniqueName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@UniqueName(type = ImageFolder.class)
public class ImageFolderInputDto implements UniquelyNamedInputDto {
    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 25)
    private String name;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 0, max = 60)
    private String description;

    @NotNull(groups = { ObjectCreation.class })
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^(png|gif|jpg)$")
    private String extension;

    public String getName() {
        return name.toLowerCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
