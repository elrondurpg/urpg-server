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

    public void createAll(int speciesDbid, List<SpeciesAbilityInputDto> input) {
        for (SpeciesAbilityInputDto record : input) {
            Ability ability = abilityService.findByName(record.getName());
            SpeciesAbility speciesAbility = new SpeciesAbility(speciesDbid, ability.getDbid(), record.isHidden());
            speciesAbilityRepository.save(speciesAbility);
        }
    }

    public void save(SpeciesAbility sa) {
        speciesAbilityRepository.save(sa);
    }

    public void delete(SpeciesAbility sa) {
        speciesAbilityRepository.delete(sa.secretGetSpecies().getDbid(), sa.getAbility().getDbid());
    }
}
