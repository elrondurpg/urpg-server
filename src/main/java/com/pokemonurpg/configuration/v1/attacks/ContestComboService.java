package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestComboRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestGenerationRepository;
import com.pokemonurpg.entities.v1.ContestCombo;
import com.pokemonurpg.entities.v1.ContestGeneration;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContestComboService {

    @Resource
    private ContestComboRepository contestComboRepository;

    @Resource
    private AttackRepository attackRepository;

    @Resource
    private ContestGenerationRepository contestGenerationRepository;

    public void update(Attack attack, ContestComboRequest input) {
        Attack secondAttack = attackRepository.findByName(input.getSecondAttack());
        ContestGeneration generation = contestGenerationRepository.findByName(input.getGeneration());
        ContestCombo existingRecord = contestComboRepository.findByFirstAttackAndSecondAttackAndGeneration(attack, secondAttack, generation);
        if (existingRecord != null) {
            if (input.getDelete()) {
                contestComboRepository.delete(existingRecord);
            }
            else {
                existingRecord.update(input);
                contestComboRepository.save(existingRecord);
            }
        }
        else {
            ContestCombo newRecord = new ContestCombo(input, attack, secondAttack, generation);
            contestComboRepository.save(newRecord);
        }
    }
}
