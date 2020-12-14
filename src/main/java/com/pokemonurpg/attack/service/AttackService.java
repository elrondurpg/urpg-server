package com.pokemonurpg.attack.service;

import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.attack.repository.AttackCategoryRepository;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.attack.repository.AttackTargetTypeRepository;
import com.pokemonurpg.contest.repository.ContestAttributeRepository;
import com.pokemonurpg.contest.repository.DPPContestMoveTypeRepository;
import com.pokemonurpg.contest.repository.ORASContestMoveTypeRepository;
import com.pokemonurpg.contest.repository.RSEContestMoveTypeRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.species.repository.TypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttackService implements NamedObjectService<Attack> {

    @Resource
    private AttackRepository attackRepository;

    @Resource
    private AttackCategoryRepository attackCategoryRepository;

    @Resource
    private AttackTargetTypeRepository attackTargetTypeRepository;

    @Resource
    private TypeRepository typeRepository;

    @Resource
    private ContestAttributeRepository contestAttributeRepository;

    @Resource
    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;

    @Resource
    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;

    @Resource
    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    public List<String> findAllNames() {
        return attackRepository.findAllNames();
    }

    public Attack findByName(String name) {
        Attack attack = attackRepository.findByName(name);
        if (attack == null) {
            attack = attackRepository.findFirstByNameStartingWith(name);
        }

        return attack;
    }

    public Attack create(AttackInputDto input) {
        Attack attack = new Attack(input);
        updateRelatedValues(attack, input);
        attackRepository.save(attack);
        return attack;
    }

    public Attack update(AttackInputDto input, int dbid) {
        Attack attack = attackRepository.findByDbid(dbid);
        if (attack != null) {
            attack.update(input);
            updateRelatedValues(attack, input);
            attackRepository.save(attack);
        }
        return attack;
    }

    private void updateRelatedValues(Attack attack, AttackInputDto input) {
        attack.setType(typeRepository.findByName(input.getType()));
        attack.setCategory(attackCategoryRepository.findByName(input.getCategory()));
        attack.setTarget(attackTargetTypeRepository.findByName(input.getTarget()));
        attack.setRseContestMoveType(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()));
        attack.setRseContestAttribute(contestAttributeRepository.findByName(input.getRseContestAttribute()));
        attack.setDppContestMoveType(dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()));
        attack.setDppContestAttribute(contestAttributeRepository.findByName(input.getDppContestAttribute()));
        attack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
        attack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
    }
}
