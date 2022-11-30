package com.pokemonurpg.configuration.v1.member.member.service;

import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.league.model.League;
import com.pokemonurpg.configuration.v1.gym.league.repository.LeagueRepository;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.GymVictory;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.model.KnownGymLeader;
import com.pokemonurpg.configuration.v1.gym.gym.repository.GymRepository;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
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
