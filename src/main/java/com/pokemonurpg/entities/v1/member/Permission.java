package com.pokemonurpg.entities.v1.member;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.pokemonurpg.configuration.v1.member.permission.PermissionInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Permission extends NamedEntity {

    @Column
    private String description;

    @ManyToMany( mappedBy = "permissions" )
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
}
