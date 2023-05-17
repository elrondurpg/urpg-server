package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.infrastructure.data.ItemRepository;
import com.pokemonurpg.infrastructure.data.TypeRepository;
import com.pokemonurpg.infrastructure.data.AttackCategoryRepository;
import com.pokemonurpg.infrastructure.data.AttackRepository;
import com.pokemonurpg.infrastructure.data.AttackTargetTypeRepository;
import com.pokemonurpg.infrastructure.data.ContestAttributeRepository;
import com.pokemonurpg.infrastructure.data.DPPContestMoveTypeRepository;
import com.pokemonurpg.infrastructure.data.ORASContestMoveTypeRepository;
import com.pokemonurpg.infrastructure.data.RSEContestMoveTypeRepository;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.Attack;
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
        attack.setDppContestMoveType(dppContestMoveTypeRepository.findByName(input.getDppContestMoveType()));
        attack.setDppContestAttribute(contestAttributeRepository.findByName(input.getDppContestAttribute()));
        attack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
        attack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
        attack.setTm(itemRepository.findByName(input.getTm()));
    }

    private void updateAssociatedValues(Attack attack, AttackInputDto input) {
        input.getContestCombos().forEach(combo -> contestComboService.update(attack, combo));
    }
}
