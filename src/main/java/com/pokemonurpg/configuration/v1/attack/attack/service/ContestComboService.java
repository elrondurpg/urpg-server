package com.pokemonurpg.configuration.v1.attack.attack.service;

import com.pokemonurpg.configuration.v1.attack.attack.input.ContestComboInputDto;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.attack.attack.model.ContestCombo;
import com.pokemonurpg.configuration.v1.attack.attack.repository.AttackRepository;
import com.pokemonurpg.configuration.v1.attack.attack.repository.ContestComboRepository;
import com.pokemonurpg.configuration.v1.contest.type.repository.ContestTypeRepository;
import com.pokemonurpg.configuration.v1.contest.type.model.ContestType;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContestComboService {

    @Resource
    private ContestComboRepository contestComboRepository;

    @Resource
    private AttackRepository attackRepository;

    @Resource
    private ContestTypeRepository contestTypeRepository;

    public void update(Attack attack, ContestComboInputDto input) {
        Attack secondAttack = attackRepository.findByName(input.getSecondAttack());
        ContestType generation = contestTypeRepository.findByName(input.getGeneration());
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
