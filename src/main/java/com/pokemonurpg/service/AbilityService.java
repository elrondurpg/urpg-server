package com.pokemonurpg.service;

import com.pokemonurpg.object.Ability;
import com.pokemonurpg.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbilityService {

    private AbilityRepository abilityRepository;

    @Autowired
    public AbilityService(AbilityRepository attackRepository) {
        this.abilityRepository = attackRepository;
    }

    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    public Optional<Ability> findByDbid(Integer dbid) {
        Optional<Ability> species = abilityRepository.findByDbid(dbid);
        return species;
    }

    public Optional<Ability> findByName(String name) {
        return abilityRepository.findByName(name);
    }

    public List<Ability> findByNameStartingWith(String name) {
        return abilityRepository.findByNameStartingWith(name);
    }

    public void save(Ability attack) {
        abilityRepository.save(attack);
    }

    public void delete(Ability attack) {
        abilityRepository.delete(attack);
    }


}
