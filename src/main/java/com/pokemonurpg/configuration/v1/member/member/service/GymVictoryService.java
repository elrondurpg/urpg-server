package com.pokemonurpg.configuration.v1.member.member.service;

import com.pokemonurpg.entities.v1.gym.Gym;
import com.pokemonurpg.entities.v1.gym.League;
import com.pokemonurpg.entities.v1.gym.LeagueRepository;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.entities.v1.member.GymVictory;
import com.pokemonurpg.entities.v1.gym.KnownGymLeader;
import com.pokemonurpg.entities.v1.gym.GymRepository;
import com.pokemonurpg.entities.v1.member.Member;
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
    private LeagueRepository gymLeagueRepository;

    public void update(GymVictoryInputDto input, Member challenger, KnownGymLeader defender) {
        Gym gym = gymRepository.findByName(input.getGym());
        League league = gymLeagueRepository.findByName(input.getLeague());

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
