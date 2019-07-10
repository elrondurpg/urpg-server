package com.pokemonurpg.service;

import com.pokemonurpg.dto.SpeciesAbilityDto;
import com.pokemonurpg.dto.SpeciesAttackDto;
import com.pokemonurpg.object.SpeciesAbility;
import com.pokemonurpg.object.SpeciesAbilityKey;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.repository.SpeciesAbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesAbilityService {

    private SpeciesAbilityRepository speciesAbilityRepository;

    @Autowired
    public SpeciesAbilityService(SpeciesAbilityRepository speciesAbilityRepository) {
        this.speciesAbilityRepository = speciesAbilityRepository;
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

    public void save(SpeciesAbility sa) {
        speciesAbilityRepository.save(sa);
    }

    public void delete(SpeciesAbility sa) {
        speciesAbilityRepository.delete(sa.secretGetSpecies().getDbid(), sa.getAbility().getDbid());
    }
}
