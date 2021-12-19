package com.pokemonurpg.stats.service;

import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.repository.GymLeagueRepository;
import com.pokemonurpg.gym.repository.GymRepository;
import com.pokemonurpg.member.models.Member;
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
