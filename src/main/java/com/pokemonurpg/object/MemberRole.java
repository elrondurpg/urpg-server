package com.pokemonurpg.object;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "member_role")
@Entity
public class MemberRole {

    @EmbeddedId
    private MemberRoleKey id;

    public MemberRole() {
    }

    public MemberRole(MemberRoleKey id) {
        this.id = id;
    }

    public MemberRoleKey getId() {
        return id;
    }

    public void setId(MemberRoleKey id) {
        this.id = id;
    }
}
