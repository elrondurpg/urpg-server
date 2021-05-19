package com.pokemonurpg.stats.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.EliteFourVictoryInputDto;
import com.pokemonurpg.stats.models.EliteFourVictory;
import com.pokemonurpg.stats.repository.EliteFourVictoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EliteFourVictoryService {

    @Resource
    private EliteFourVictoryRepository eliteFourVictoryRepository;

    public void update(EliteFourVictoryInputDto input, Member challenger) {
        EliteFourVictory existingRecord = eliteFourVictoryRepository.findByChallengerAndDefender(challenger, input.getDefender());

        if (existingRecord != null) {
            if (input.getDelete()) {
                eliteFourVictoryRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                eliteFourVictoryRepository.save(existingRecord);
            }
        }
        else {
            EliteFourVictory newRecord = new EliteFourVictory(input, challenger);
            eliteFourVictoryRepository.save(newRecord);
        }
    }
}
