package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.dto.species.response.SpeciesAbilityDto;
import com.pokemonurpg.object.Ability;
import com.pokemonurpg.object.SpeciesAbility;
import com.pokemonurpg.object.SpeciesAbilityKey;
import com.pokemonurpg.repository.SpeciesAbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesAbilityService {

    private SpeciesAbilityRepository speciesAbilityRepository;
    private AbilityService abilityService;

    @Autowired
    public SpeciesAbilityService(SpeciesAbilityRepository speciesAbilityRepository, AbilityService abilityService) {
        this.speciesAbilityRepository = speciesAbilityRepository;
        this.abilityService = abilityService;
    }

    public List<SpeciesAbility> findAll() {
        return speciesAbilityRepository.findAll();
    }

    public List<SpeciesAbilityDto> findBySpeciesDbid(int dbid) {
        List<SpeciesAbilityDto> speciesAbilityDtos = new ArrayList<>();
        List<SpeciesAbility> speciesAbilities = speciesAbilityRepository.findByIdSpeciesDbid(dbid);
        for (SpeciesAbility speciesAbility : speciesAbilities) {
            SpeciesAbilityDto speciesAbilityDto = new SpeciesAbilityDto(speciesAbility);
            speciesAbilityDtos.add(speciesAbilityDto);
        }
        return speciesAbilityDtos;
    }

    public Optional<SpeciesAbility> findBySpeciesAbilityKey(SpeciesAbilityKey key) {
        return speciesAbilityRepository.findById(key);
    }

    public void create(int speciesDbid, SpeciesAbilityInputDto input) {
        Ability ability = abilityService.findByName(input.getName());
        SpeciesAbility speciesAbility = new SpeciesAbility(speciesDbid, ability.getDbid(), input.isHidden());
        speciesAbilityRepository.save(speciesAbility);
    }

    public void createAll(int speciesDbid, List<SpeciesAbilityInputDto> input) {
        for (SpeciesAbilityInputDto record : input) {
            if (!record.isDeleted()) {
                create(speciesDbid, record);
            }
        }
    }

    public void update(SpeciesAbility existingRecord, SpeciesAbilityInputDto input) {
        if (input.isDeleted()) {
            speciesAbilityRepository.delete(existingRecord);
        }
        else {
            if (input.isHidden() != null) {
                existingRecord.setHidden(input.isHidden());
            }
            speciesAbilityRepository.save(existingRecord);
        }
    }

    public void updateAll(int speciesDbid, List<SpeciesAbilityInputDto> input) {
        if (input != null) {
            for (SpeciesAbilityInputDto record : input) {
                Ability ability = abilityService.findByName(record.getName());

                SpeciesAbility existingRecord = speciesAbilityRepository.findByIdSpeciesDbidAndIdAbilityDbid(speciesDbid, ability.getDbid());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else if (!record.isDeleted()){
                    create(speciesDbid, record);
                }
            }
        }
    }
}
