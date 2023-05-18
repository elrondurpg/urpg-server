package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberRequest;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
@Table(name = "known_elite_four_member")
public class EliteFourMember implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public EliteFourMember() {

    }

    public EliteFourMember(String name) {
        setName(name);
    }

    public EliteFourMember(EliteFourMemberRequest input) {
        this.update(input);
    }

    public void update(EliteFourMemberRequest input) {
        setName(input.getName());
    }

    @Override
    public Integer getDbid() {
        return dbid;
    }

    @Override
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
}
