package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymLeague;
import com.pokemonurpg.entities.KnownGymLeader;
import com.pokemonurpg.infrastructure.data.GymLeagueRepository;
import com.pokemonurpg.infrastructure.data.GymRepository;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.input.GymVictoryInputDto;
import com.pokemonurpg.stats.models.GymVictory;
import com.pokemonurpg.stats.repository.GymVictoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GymVictoryService {

    @Resource
    private GymVictoryRepository gymVictoryRepository;

    @Resource
    private GymRepository gymRepository;

    @Resource
    private GymLeagueRepository gymLeagueRepository;

    public void update(GymVictoryInputDto input, Member challenger, KnownGymLeader defender) {
        Gym gym = gymRepository.findByName(input.getGym());
        GymLeague league = gymLeagueRepository.findByName(input.getLeague());

        GymVictory existingRecord = gymVictoryRepository.findByChallengerAndDefenderAndGymAndLeague(challenger, defender, gym, league);

        if (existingRecord != null) {
            if (input.getDelete()) {
                gymVictoryRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                gymVictoryRepository.save(existingRecord);
            }
        }
        else {
            GymVictory newRecord = new GymVictory(input, challenger, defender, gym, league);
            gymVictoryRepository.save(newRecord);

        }
    }
}
