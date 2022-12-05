package com.pokemonurpg.entities.v1.member;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pokemonurpg.configuration.v1.member.role.RoleInputDto;
import com.pokemonurpg.entities.v1.shared.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends NamedEntity {

    @ManyToMany(
            targetEntity= Permission.class,
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="ROLE_PERMISSION",
            joinColumns=@JoinColumn(name="ROLE_DBID"),
            inverseJoinColumns=@JoinColumn(name="PERMISSION_DBID")
    )
    @JsonIgnoreProperties("roles")
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<Member> members;

    public Role() {
    }

    public Role(RoleInputDto input) {
        this.update(input);
    }

    public void update(RoleInputDto input) {
        setName(input.getName());
    }
}
