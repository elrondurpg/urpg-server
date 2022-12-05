package com.pokemonurpg.entities.v1.site;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pokemonurpg.configuration.v1.site.image.input.ImageFolderInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ImageFolder extends NamedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String extension;

    public ImageFolder() {

    }

    public ImageFolder(ImageFolderInputDto input) {
        this.update(input);
    }

    public void update(ImageFolderInputDto input) {
        setName(input.getName());
        setDescription(input.getDescription());
        setExtension(input.getExtension());
    }
}
