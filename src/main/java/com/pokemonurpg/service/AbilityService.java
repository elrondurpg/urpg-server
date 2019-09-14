package com.pokemonurpg.service;

import com.pokemonurpg.object.Ability;
import com.pokemonurpg.repository.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AbilityService {

    private AbilityRepository abilityRepository;

    @Autowired
    public AbilityService(AbilityRepository abilityRepository) {
        this.abilityRepository = abilityRepository;
    }

    public List<Object> findAll() {
        return abilityRepository.findAllNames();
    }

    public Ability findByDbid(Integer dbid) {
        Ability ability = abilityRepository.findByDbid(dbid);
        return ability;
    }

    public Ability findByName(String name) {
        Ability ability = abilityRepository.findByName(name);
        if (ability != null) {
            return ability;
        }
        else {
            List<Ability> results = abilityRepository.findByNameStartingWith(name);
            if (!results.isEmpty()) {
                return results.get(0);
            }
            else return null;
        }
    }

    public List<Ability> findByNameStartingWith(String name) {
        return abilityRepository.findByNameStartingWith(name);
    }

    public Errors createAbility(Ability input) {
        Errors errors = validateAbilityCreate(input);
        if (!errors.hasErrors()) {
            abilityRepository.save(input);
        }
        return errors;
    }

    public Errors updateAbility(Ability input) {
        Errors errors = validateAbilityUpdate(input);
        if (!errors.hasErrors()) {
            Ability existingAbility = abilityRepository.findByName(input.getName());
            if (input.getName() != null) {
                existingAbility.setName(input.getName());
            }
            if (input.getDescription() != null) {
                existingAbility.setDescription(input.getDescription());
            }
            abilityRepository.save(existingAbility);
        }
        return errors;
    }

    public Errors validateAbilityCreate(Ability input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Ability existingRecord = abilityRepository.findByName(input.getName());
        if (existingRecord == null) {
            if (input.getName() == null || input.getName().length() < 3 || input.getName().length() > 20) {
                errors.reject("Ability name " + input.getName() + " is invalid.");
            }

            if (input.getDescription() == null || input.getDescription().length() < 3 || input.getDescription().length() > 160) {
                errors.reject("Description " + input.getDescription() + " is invalid.");
            }
        }
        else {
            errors.reject("Ability " + input.getName() + " already exists.");
        }

        return errors;
    }

    public Errors validateAbilityUpdate(Ability input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Ability existingRecord = abilityRepository.findByName(input.getName());
        if (existingRecord != null) {
            if (input.getName() != null && (input.getName().length() < 3 || input.getName().length() > 20)) {
                errors.reject("Ability name " + input.getName() + " is invalid.");
            }

            if (input.getDescription() != null && (input.getDescription().length() < 3 || input.getDescription().length() > 160)) {
                errors.reject("Description " + input.getDescription() + " is invalid.");
            }
        }
        else {
            errors.reject("Ability " + input.getName() + " doesn't exist.");
        }

        return errors;
    }

}
