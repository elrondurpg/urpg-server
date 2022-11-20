package com.pokemonurpg.stats.service;

import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.model.KnownEliteFourMember;
import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.EliteFourVictory;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.stats.repository.EliteFourVictoryRepository;
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

    public void update(EliteFourVictoryInputDto input, Member challenger, KnownEliteFourMember defender) {
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
