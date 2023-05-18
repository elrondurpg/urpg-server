package com.pokemonurpg.configuration.v1.gymleagues;

import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeagueRepository;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
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
        GymLeague gymLeague = findByNameExact(name);
        if (gymLeague == null && name != null) {
            return gymLeagueRepository.findFirstByNameStartingWith(name);
        }
        else return gymLeague;
    }

    @Override
    public GymLeague findByNameExact(String name) {
        return gymLeagueRepository.findByName(name);
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
