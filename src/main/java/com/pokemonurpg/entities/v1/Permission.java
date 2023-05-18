package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.model.NamedObject;
import com.pokemonurpg.configuration.v1.permissions.PermissionInputDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Permission implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany( mappedBy = "permissions" )
    @JsonIgnoreProperties({ "permissions", "members" })
    private Set<Role> roles;

    public Permission() {
    }

    public Permission(PermissionInputDto input) {
        this.update(input);
    }

    public void update(PermissionInputDto input) {
        setName(input.getName());
        setDescription(input.getDescription());
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

    public Set<Role> getRoles() {
        return roles;
    }

    void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }
}
