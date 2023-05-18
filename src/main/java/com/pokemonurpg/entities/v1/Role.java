package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.roles.RoleRequest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class Role implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

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
    @JsonView(value = { View.MemberView.Secure.class })
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties({ "roles", "salt", "accessToken", "refreshToken", "sessionExpire",
                            "money", "wins", "losses", "draws", "joinDate", "pokemon",
                            "items", "badges", "championRecords", "legendaryProgress",
                            "earnedLegendaries", "gyms", "banned", "banExpiration",
                            "eliteFourTerms", "championTerms", "eliteFourVictories", "championVictories", "gymVictories" })
    private Set<Member> members;

    public Role() {
    }

    public Role(RoleRequest input) {
        this.update(input);
    }

    public void update(RoleRequest input) {
        setName(input.getName());
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    void setMembers(Set<Member> members) {
        this.members = members;
    }
}
