package com.pokemonurpg.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.lib.v1.models.NamedObject;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class GymLeague implements NamedObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @OneToMany(mappedBy="league")
    @JsonIgnoreProperties("league")
    private List<GymLeaderRecord> gyms;

    public GymLeague() {}
    public GymLeague(GymLeagueRequest input) {
        this.update(input);
    }

    public void update(GymLeagueRequest input) {
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

    public List<GymLeaderRecord> getGyms() {
        return gyms;
    }

    public void setGyms(List<GymLeaderRecord> gyms) {
        this.gyms = gyms;
    }
}
