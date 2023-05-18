package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.featureflags.FlagInputDto;

import javax.persistence.*;

@Entity
public class Flag implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String type;

    @Column
    private String value;

    public Flag() {
    }

    public Flag(FlagInputDto input) {
        this.update(input);
    }

    public void update(FlagInputDto input) {
        setName(input.getName());
        setDescription(input.getDescription());
        setType(input.getType());
        setValue(input.getValue());
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
        if (name != null) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonIgnore
    public boolean getBooleanValue() {
        return "TRUE".equalsIgnoreCase(value);
    }
}
