package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymLeague;
import com.pokemonurpg.entities.v1.GymLeader;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeagueRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymRepository;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.GymVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.GymVictoryRepository;
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

    public void update(GymVictoryRequest input, Member challenger, GymLeader defender) {
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
