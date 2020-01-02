package com.pokemonurpg.object.trainer;

import javax.persistence.*;

@Table(name = "member_role")
@Entity
public class MemberRole {

    @EmbeddedId
    private MemberRoleKey id;

    @ManyToOne
    @JoinColumn(name="member_dbid", insertable = false, updatable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name="role_dbid", insertable = false, updatable = false)
    private Role role;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
