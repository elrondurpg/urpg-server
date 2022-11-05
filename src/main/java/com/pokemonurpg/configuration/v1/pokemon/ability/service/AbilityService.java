package com.pokemonurpg.configuration.v1.pokemon.ability.service;

import com.pokemonurpg.configuration.v1.pokemon.ability.input.AbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.ability.model.Ability;
import com.pokemonurpg.configuration.v1.pokemon.ability.repository.AbilityRepository;
import com.pokemonurpg.core.service.NamedObjectService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AbilityService implements NamedObjectService<Ability> {

    @Resource
    private AbilityRepository abilityRepository;

    public List<String> findAllNames() {
        return abilityRepository.findAllNames();
    }

    public Ability findByDbid(Integer dbid) {
        return abilityRepository.findByDbid(dbid);
    }

    public Ability findByName(String name) {
        Ability ability = abilityRepository.findByName(name);
        if (ability == null) {
            ability = abilityRepository.findFirstByNameStartingWith(name);
        }
        return ability;
    }

    @Override
    public Ability findByNameExact(String name) {
        return abilityRepository.findByName(name);
    }

    public Ability create(AbilityInputDto input) {
        Ability ability = new Ability(input);
        abilityRepository.save(ability);
        return ability;
    }

    public Ability update(AbilityInputDto input, int dbid) {
        Ability ability = abilityRepository.findByDbid(dbid);
        if (ability != null) {
            ability.update(input);
            abilityRepository.save(ability);
        }
        return ability;
    }

}
