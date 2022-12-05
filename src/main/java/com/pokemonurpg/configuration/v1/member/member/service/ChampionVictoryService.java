package com.pokemonurpg.configuration.v1.member.member.service;

import com.pokemonurpg.entities.v1.gym.KnownChampion;
import com.pokemonurpg.configuration.v1.member.member.input.ChampionVictoryInputDto;
import com.pokemonurpg.entities.v1.member.ChampionVictory;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.stats.repository.ChampionVictoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChampionVictoryService {

    @Resource
    private ChampionVictoryRepository championVictoryRepository;

    public List<ChampionVictory> findAll() {
        return championVictoryRepository.findAll();
    }

    public void update(ChampionVictoryInputDto input, Member challenger, KnownChampion defender) {
        ChampionVictory existingRecord = championVictoryRepository.findByChallengerAndDefender(challenger, defender);

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
            ChampionVictory newRecord = new ChampionVictory(input, challenger, defender);
            championVictoryRepository.save(newRecord);

        }
    }
}
