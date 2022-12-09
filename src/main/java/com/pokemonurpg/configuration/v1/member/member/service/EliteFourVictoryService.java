package com.pokemonurpg.configuration.v1.member.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.entities.v1.gym.KnownEliteFourMember;
import com.pokemonurpg.entities.v1.member.EliteFourVictory;
import com.pokemonurpg.entities.v1.member.EliteFourVictoryRepository;
import com.pokemonurpg.entities.v1.member.Member;

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
