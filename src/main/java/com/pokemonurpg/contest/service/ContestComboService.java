package com.pokemonurpg.contest.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.contest.input.ContestComboInputDto;
import com.pokemonurpg.contest.models.ContestCombo;
import com.pokemonurpg.contest.models.ContestType;
import com.pokemonurpg.contest.repository.ContestComboRepository;
import com.pokemonurpg.contest.repository.ContestTypeRepository;

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
