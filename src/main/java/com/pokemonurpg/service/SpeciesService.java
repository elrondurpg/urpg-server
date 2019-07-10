package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.SpeciesDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.*;

@Service
public class SpeciesService {

    private SpeciesRepository speciesRepository;

    private SpeciesAttackService speciesAttackService;
    private AttackService attackService;

    private SpeciesAbilityService speciesAbilityService;
    private AbilityService abilityService;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository, SpeciesAttackService speciesAttackService, AttackService attackService, SpeciesAbilityService speciesAbilityService, AbilityService abilityService) {
        this.speciesRepository = speciesRepository;
        this.speciesAttackService = speciesAttackService;
        this.attackService = attackService;
        this.speciesAbilityService = speciesAbilityService;
        this.abilityService = abilityService;
    }

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public List<Species> findAllByDexno(Integer dexno) {
        return speciesRepository.findByDexno(dexno);
    }

    public Optional<Species> findByDexno(Integer dexno) {
        List<Species> results = speciesRepository.findByDexno(dexno);
        if (results != null && !results.isEmpty()) {
            Species species = results.get(0);
            return Optional.of(species);
        }
        return Optional.empty();
    }

    public SpeciesDto findByName(String name) {
        SpeciesDto result = new SpeciesDto();
        Optional<Species> speciesOptional = speciesRepository.findByName(name);
        if (speciesOptional.isPresent()) {
            Species species = speciesOptional.get();
            result = buildSpeciesDto(species);
            result.setStatus(200);
        }
        else {
            List<Species> results = speciesRepository.findByNameStartingWith(name);
            if (results != null && !results.isEmpty()) {
                Species species = results.get(0);
                result = buildSpeciesDto(species);
                result.setStatus(200);
            }
            else {
                result.setStatus(404);
            }
        }

        return result;
    }

    public SpeciesDto buildSpeciesDto(Species species) {
        if (species != null) {
            SpeciesDto speciesDto = new SpeciesDto(species);
            int dbid = species.getDbid();
            speciesDto.setSpeciesAttacks(speciesAttackService.findBySpeciesDbid(dbid));
            speciesDto.setSpeciesAbilities(speciesAbilityService.findBySpeciesDbid(dbid));
            /*speciesDto.setCosmeticForms(cosmeticFormService.findByBaseDbid(dbid));
            speciesDto.setNextSpecies(buildNextSpeciesTabDto(species));
            speciesDto.setPrevSpecies(buildPrevSpeciesTabDto(species));
            speciesDto.setAlteredForms(buildAlteredFormDtoList(species));
            speciesDto.setAlteredFormMethod(alteredFormMethodService.findByBaseDex(species.getDexno());*/

            return speciesDto;
        }
        else return new SpeciesDto();
    }

    /*public Optional<Species> findByDbid(Integer dbid) {
        Optional<Species> species = speciesRepository.findByDbid(dbid);
        return species;
    }

    public SpeciesDto findByNameStartingWith(String name) {
        List<Species> results = speciesRepository.findByNameStartingWith(name);
        if (results != null && !results.isEmpty()) {
            Species species = results.get(0);
            return new SpeciesDto(species);
        }
        else return null;
    }

    public Errors save(Species species) {
        HashMap<String, String> errorMap = new HashMap<>();
        MapBindingResult errors = new MapBindingResult(errorMap, "");
        save(species, errors);
        return errors;
    }

    public Errors save(Species species, Errors errors) {
        speciesRepository.save(species);

        speciesAttackService.saveSpeciesAttacksFromSpecies(species, speciesRepository.findByName(species.getName()).orElse(new Species()));

        Map<String, SpeciesAbility> currentAbilities = getCurrentAbilities(species);
        Map<String, SpeciesAbility> newAbilities = getNewAbilities(species);

        if (newAbilities != null && !newAbilities.isEmpty()) {
            for (SpeciesAbility sa : currentAbilities.values()) {
                if (!newAbilities.containsKey((sa.getAbility().getName()))) {
                    speciesAbilityService.delete(sa);
                }
            }

            for (SpeciesAbility sa : newAbilities.values()) {
                Optional<Species> speciesEntity = findByName(species.getName());
                Optional<Ability> ability = abilityService.findByName(sa.getAbility().getName());
                if (speciesEntity.isPresent() && ability.isPresent()) {
                    sa.setAbility(null);
                    sa.setId(new SpeciesAbilityKey(speciesEntity.get().getDbid(), ability.get().getDbid()));
                    speciesAbilityService.save(sa);
                }
            }
        }

        Map<String, SpeciesAttack> currentAttacks = getCurrentAttacks(species);
        Map<String, SpeciesAttack> newAttacks = getNewAttacks(species);

        if (newAttacks != null && !newAttacks.isEmpty()) {
            for (SpeciesAttack sa : currentAttacks.values()) {
                if (!newAttacks.containsKey(sa.getAttack().getName())) {
                    speciesAttackService.delete(sa);
                }
            }

            // TODO implement error handling, pass Errors back through return
            for (SpeciesAttack sa : newAttacks.values()) {
                Optional<Species> speciesEntity = findByName(species.getName());
                Optional<Attack> attack = attackService.findByName(sa.getAttack().getName());
                if (speciesEntity.isPresent() && attack.isPresent()) {
                    sa.setAttack(null);
                    sa.setId(new SpeciesAttackKey(speciesEntity.get().getDbid(), attack.get().getDbid()));
                    speciesAttackService.save(sa);
                }
            }
        }

        return errors;
    }

    public Map<String, SpeciesAbility> getCurrentAbilities(Species species) {
        Map<String, SpeciesAbility> map = new HashMap<>();
        if (species != null) {
            Optional<Species> prototype = findByName(species.getName());

            if (prototype.isPresent()) {
                List<SpeciesAbility> list = prototype.get().getAbilities();
                if (list != null) {
                    for (SpeciesAbility sa : list) {
                        if (sa.getAbility() != null && sa.getAbility().getName() != null) {
                            map.put(sa.getAbility().getName(), sa);
                        }
                    }
                }
            }
        }
        return map;
    }

    public Map<String, SpeciesAttack> getCurrentAttacks(Species species) {
        Map<String, SpeciesAttack> map = new HashMap<>();
        if (species != null) {
            Optional<Species> prototype = findByName(species.getName());

            if (prototype.isPresent()) {
                List<SpeciesAttack> list = prototype.get().getAttacks();
                if (list != null) {
                    for (SpeciesAttack sa : list) {
                        if (sa.getAttack() != null && sa.getAttack().getName() != null) {
                            map.put(sa.getAttack().getName(), sa);
                        }
                    }
                }
            }
        }
        return map;
    }

    public Map<String, SpeciesAbility> getNewAbilities(Species species) {
        Map<String, SpeciesAbility> map = new HashMap<>();
        if (species != null) {
            List <SpeciesAbility> list = species.getAbilities();
            if (list != null) {
                for (SpeciesAbility sa : list) {
                    if (sa.getAbility() != null && sa.getAbility().getName() != null) {
                        map.put(sa.getAbility().getName(), sa);
                    }
                }
            }
        }
        return map;
    }

    public Map<String, SpeciesAttack> getNewAttacks(Species species) {
        Map<String, SpeciesAttack> map = new HashMap<>();
        if (species != null) {
            List<SpeciesAttack> list = species.getAttacks();
            if (list != null) {
                for (SpeciesAttack sa : list) {
                    if (sa.getAttack() != null && sa.getAttack().getName() != null) {
                        map.put(sa.getAttack().getName(), sa);
                    }
                }
            }
        }
        return map;
    }

    public void delete(Species species) {
        Map<String, SpeciesAttack> currentAttacks = getCurrentAttacks(species);
        for (SpeciesAttack sa : currentAttacks.values()) {
            speciesAttackService.delete(sa);
        }
        speciesRepository.delete(species);
    }*/

}
