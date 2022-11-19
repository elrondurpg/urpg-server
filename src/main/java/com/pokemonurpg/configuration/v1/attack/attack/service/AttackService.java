package com.pokemonurpg.configuration.v1.attack.attack.service;

import com.pokemonurpg.configuration.v1.attack.category.repository.AttackCategoryRepository;
import com.pokemonurpg.configuration.v1.attack.target.repository.AttackTargetTypeRepository;
import com.pokemonurpg.configuration.v1.attack.attack.input.AttackInputDto;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.pokemon.type.repository.TypeRepository;
import com.pokemonurpg.configuration.v1.contest.attribute.repository.ContestAttributeRepository;
import com.pokemonurpg.configuration.v1.contest.oras.repository.OrasContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.contest.rse.repository.RseContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.item.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService extends NamedConfigurationService<Attack, AttackInputDto> {

    private AttackCategoryRepository attackCategoryRepository;
    private AttackTargetTypeRepository attackTargetTypeRepository;
    private TypeRepository typeRepository;
    private ContestAttributeRepository contestAttributeRepository;
    private RseContestMoveTypeRepository rseContestMoveTypeRepository;
    private OrasContestMoveTypeRepository orasContestMoveTypeRepository;
    private ItemRepository itemRepository;
    private ContestComboService contestComboService;

    @Autowired
    public AttackService(NamedConfigurationRepository<Attack> repository,
            AttackCategoryRepository attackCategoryRepository, AttackTargetTypeRepository attackTargetTypeRepository,
            TypeRepository typeRepository, ContestAttributeRepository contestAttributeRepository,
            RseContestMoveTypeRepository rseContestMoveTypeRepository,
            OrasContestMoveTypeRepository orasContestMoveTypeRepository, ItemRepository itemRepository,
            ContestComboService contestComboService) {
        super(repository, Attack.class);
        this.attackCategoryRepository = attackCategoryRepository;
        this.attackTargetTypeRepository = attackTargetTypeRepository;
        this.typeRepository = typeRepository;
        this.contestAttributeRepository = contestAttributeRepository;
        this.rseContestMoveTypeRepository = rseContestMoveTypeRepository;
        this.orasContestMoveTypeRepository = orasContestMoveTypeRepository;
        this.itemRepository = itemRepository;
        this.contestComboService = contestComboService;
    }

    protected void updateBase(Attack model, AttackInputDto input) {
        setIfNotNull(input.getName(), model::setName);
        setIfNotNull(input.getDescription(), model::setDescription);
        setIfNotNull(input.getPower(), model::setPower);
        setIfNotNull(input.getAccuracy(), model::setAccuracy);
        setIfNotNull(input.getPp(), model::setPp);
        setIfNotNull(input.getContact(), model::setContact);
        setIfNotNull(input.getSnatch(), model::setSnatch);
        setIfNotNull(input.getSubstitute(), model::setSubstitute);
        setIfNotNull(input.getSheerForce(), model::setSheerForce);
        setIfNotNull(input.getMagicCoat(), model::setMagicCoat);
    }

    protected void updateEmbeddedValues(Attack attack, AttackInputDto input) {
        attack.setType(typeRepository.findByName(input.getType()));
        attack.setCategory(attackCategoryRepository.findByName(input.getCategory()));
        attack.setTarget(attackTargetTypeRepository.findByName(input.getTarget()));
        attack.setRseContestMoveType(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType()));
        attack.setRseContestAttribute(contestAttributeRepository.findByName(input.getRseContestAttribute()));
        attack.setOrasContestMoveType(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType()));
        attack.setOrasContestAttribute(contestAttributeRepository.findByName(input.getOrasContestAttribute()));
        attack.setTm(itemRepository.findByName(input.getTm()));
    }

    protected void updateAssociatedValues(Attack attack, AttackInputDto input) {
        input.getContestCombos().forEach(combo -> contestComboService.update(attack, combo));
    }

    @Override
    protected void deleteAssociatedValues(Attack model) {
        // TODO Auto-generated method stub
        
    }
}
