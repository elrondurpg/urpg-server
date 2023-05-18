package com.pokemonurpg.configuration.v1.abilities;

import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.Ability;
import com.pokemonurpg.infrastructure.v1.data.jpa.AbilityRepository;
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

    public Ability create(AbilityRequest input) {
        Ability ability = new Ability(input);
        abilityRepository.save(ability);
        return ability;
    }

    public Ability update(AbilityRequest input, int dbid) {
        Ability ability = abilityRepository.findByDbid(dbid);
        if (ability != null) {
            ability.update(input);
            abilityRepository.save(ability);
        }
        return ability;
    }

}
