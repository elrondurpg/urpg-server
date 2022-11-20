package com.pokemonurpg.configuration.v1.gym.league.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.league.input.LeagueInputDto;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.gym.league.repository.LeagueRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService extends SimpleNamedConfigurationService<League, LeagueInputDto> {

    @Autowired
    public LeagueService(LeagueRepository repo) {
        super(repo, League.class);
    }
}
