package com.pokemonurpg.stats.v1;

import com.pokemonurpg.entities.v1.EliteFourMember;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.EliteFourVictory;
import com.pokemonurpg.infrastructure.v1.data.jpa.EliteFourVictoryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EliteFourVictoryService {

    @Resource
    private EliteFourVictoryRepository eliteFourVictoryRepository;

    public List<EliteFourVictory> findAll() {
        return eliteFourVictoryRepository.findAll();
    }

    public void update(EliteFourVictoryRequest input, Member challenger, EliteFourMember defender) {
        EliteFourVictory existingRecord = eliteFourVictoryRepository.findByChallengerAndDefender(challenger, defender);

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
            EliteFourVictory newRecord = new EliteFourVictory(input, challenger, defender);
            eliteFourVictoryRepository.save(newRecord);
        }
    }
}
