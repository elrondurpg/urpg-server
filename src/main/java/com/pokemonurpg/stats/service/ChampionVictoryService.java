package com.pokemonurpg.stats.service;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.input.ChampionVictoryInputDto;
import com.pokemonurpg.stats.models.ChampionVictory;
import com.pokemonurpg.stats.repository.ChampionVictoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChampionVictoryService {

    @Resource
    private ChampionVictoryRepository championVictoryRepository;

    public void update(ChampionVictoryInputDto input, Member challenger) {
        ChampionVictory existingRecord = championVictoryRepository.findByChallengerAndDefender(challenger, input.getDefender());

        if (existingRecord != null) {
            if (input.getDelete()) {
                championVictoryRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                championVictoryRepository.save(existingRecord);
            }
        }
        else {
            ChampionVictory newRecord = new ChampionVictory(input, challenger);
            championVictoryRepository.save(newRecord);

        }
    }
}
