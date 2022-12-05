package com.pokemonurpg.configuration.v1.gym.league.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.league.input.LeagueInputDto;
import com.pokemonurpg.entities.v1.gym.League;
import com.pokemonurpg.entities.v1.gym.LeagueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService extends SimpleNamedConfigurationService<League, LeagueInputDto> {

    @Autowired
    public LeagueService(LeagueRepository repo) {
        super(repo, League.class);
    }
}
