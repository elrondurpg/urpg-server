package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.infrastructure.v1.data.jpa.ItemRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.TypeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackCategoryRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackTargetTypeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestAttributeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ORASContestMoveTypeRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.RSEContestMoveTypeRepository;
import com.pokemonurpg.lib.v1.service.NamedObjectService;
import com.pokemonurpg.entities.v1.Attack;
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
    private ItemRepository itemRepository;

    @Resource
    private ContestComboService contestComboService;

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

    @Override
    public Attack findByNameExact(String name) {
        return attackRepository.findByName(name);
    }

    public Attack create(AttackInputDto input) {
        Attack attack = new Attack(input);
        updateEmbeddedValues(attack, input);
        attackRepository.save(attack);
        updateAssociatedValues(attack, input);
        return attack;
    }

    public Attack update(AttackInputDto input, int dbid) {
        Attack attack = attackRepository.findByDbid(dbid);
        if (attack != null) {
            attack.update(input);
            updateEmbeddedValues(attack, input);
            attackRepository.save(attack);
            updateAssociatedValues(attack, input);
        }
        return attack;
    }

    private void updateEmbeddedValues(Attack attack, AttackInputDto input) {
        attack.setType(typeRepository.findByName(input.getType()));
        attack.setCategory(attackCategoryRepository.findByName(input.getCategory()));
        attack.setTarget(attackTargetTypeRepository.findByName(input.getTarget()));
        attack.setRseContestMoveType(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()));
        attack.setRseContestAttribute(contestAttributeRepository.findByName(input.getRseContestAttribute()));
        attack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
        attack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
        attack.setTm(itemRepository.findByName(input.getTm()));
    }

    private void updateAssociatedValues(Attack attack, AttackInputDto input) {
        input.getContestCombos().forEach(combo -> contestComboService.update(attack, combo));
    }
}
