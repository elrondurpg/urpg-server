package com.pokemonurpg.contest.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.contest.input.ContestComboInputDto;
import com.pokemonurpg.contest.models.ContestCombo;
import com.pokemonurpg.contest.repository.ContestComboRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ContestComboService {

    @Resource
    private ContestComboRepository contestComboRepository;

    @Resource
    private AttackRepository attackRepository;

    public void update(Attack attack, ContestComboInputDto input) {
        Attack secondAttack = attackRepository.findByName(input.getSecondAttack());
        ContestCombo existingRecord = contestComboRepository.findByFirstAttackAndSecondAttackAndIdContestType(attack, secondAttack, input.getContestType());
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
            ContestCombo newRecord = new ContestCombo(input, attack, secondAttack);
            contestComboRepository.save(newRecord);
        }
    }
}
