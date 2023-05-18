package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodRequest;

import javax.persistence.*;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
@Table(name = "obtained")
public class CaptureMethod implements NamedObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    public CaptureMethod() {
    }

    public CaptureMethod(CaptureMethodRequest input) {
        this.update(input);
    }

    public void update(CaptureMethodRequest input) {
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
        this.name = name;
    }
}
