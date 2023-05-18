package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.KnownChampion;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.ChampionVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionVictoryRepository;
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
