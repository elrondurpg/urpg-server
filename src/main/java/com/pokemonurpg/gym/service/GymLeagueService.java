package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.input.GymLeagueInputDto;
import com.pokemonurpg.gym.repository.GymLeagueRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GymLeagueService implements NamedObjectService<GymLeague> {

    @Resource
    private GymLeagueRepository gymLeagueRepository;

    public List<String> findAllNames() {
        return gymLeagueRepository.findAllNames();
    }

    public GymLeague findByDbid(int dbid) {
        return gymLeagueRepository.findByDbid(dbid);
    }

    public GymLeague findByName(String name) {
        GymLeague gymLeague = gymLeagueRepository.findByName(name);
        if (gymLeague == null && name != null) {
            return gymLeagueRepository.findFirstByNameStartingWith(name);
        }
        else return gymLeague;
    }

    public GymLeague create(GymLeagueInputDto input) {
        GymLeague gymLeague = new GymLeague(input);
        gymLeagueRepository.save(gymLeague);
        return gymLeague;
    }

    public GymLeague update(GymLeagueInputDto input, int dbid) {
        GymLeague gymLeague = gymLeagueRepository.findByDbid(dbid);
        if (gymLeague != null) {
            gymLeague.update(input);
            gymLeagueRepository.save(gymLeague);
        }
        return gymLeague;
    }
}