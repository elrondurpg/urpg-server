package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.KnownChampion;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.stats.input.ChampionVictoryInputDto;
import com.pokemonurpg.stats.models.ChampionVictory;
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