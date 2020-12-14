package com.pokemonurpg.gym.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.pokemonurpg.View;
import com.pokemonurpg.gym.input.GymLeagueInputDto;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonView(value = { View.MemberView.Summary.class })
public class GymLeague {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer dbid;

    @Column
    private String name;

    @OneToMany(mappedBy="league")
    @JsonIgnoreProperties("league")
    private List<Gym> gyms;

    public GymLeague() {}
    public GymLeague(GymLeagueInputDto input) {
        this.update(input);
    }

    public void update(GymLeagueInputDto input) {
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

    public List<Gym> getGyms() {
        return gyms;
    }

    public void setGyms(List<Gym> gyms) {
        this.gyms = gyms;
    }
}
