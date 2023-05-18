package com.pokemonurpg.entities.v1;

import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderRequest;

import javax.persistence.*;

@Entity
public class ImageFolder implements NamedObject {
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

    public ImageFolder(ImageFolderRequest input) {
        this.update(input);
    }

    public void update(ImageFolderRequest input) {
        setName(input.getName());
        setDescription(input.getDescription());
        setExtension(input.getExtension());
    }

    public Integer getDbid() {
        return dbid;
    }

    public void setDbid(Integer dbid) {
        this.dbid = dbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        if (extension != null) {
            this.extension = extension;
        }
    }
}
