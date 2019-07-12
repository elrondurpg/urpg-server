package com.pokemonurpg.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.dto.ResponseDto;
import com.pokemonurpg.dto.species.SpeciesDto;
import com.pokemonurpg.dto.species.SpeciesPageTabDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpeciesService {

    private SpeciesRepository speciesRepository;

    private SpeciesAttackService speciesAttackService;
    private AttackService attackService;

    private SpeciesAbilityService speciesAbilityService;
    private AbilityService abilityService;

    private CosmeticFormService cosmeticFormService;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository, SpeciesAttackService speciesAttackService, AttackService attackService,
                          SpeciesAbilityService speciesAbilityService, AbilityService abilityService,
                          CosmeticFormService cosmeticFormService) {
        this.speciesRepository = speciesRepository;
        this.speciesAttackService = speciesAttackService;
        this.attackService = attackService;
        this.speciesAbilityService = speciesAbilityService;
        this.abilityService = abilityService;
        this.cosmeticFormService = cosmeticFormService;
    }

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public List<Species> findAllByDexno(Integer dexno) {
        return speciesRepository.findByDexno(dexno);
    }

    public List<SpeciesDto> findByDexno(Integer dexno) {
        List<Species> results = speciesRepository.findByDexno(dexno);
        List<SpeciesDto> responseList = new ArrayList<>();
        if (results != null && !results.isEmpty()) {
            for (Species species : results) {
                responseList.add(buildSpeciesDto(species));
            }
        }
        return responseList;
    }

    public SpeciesDto findByName(String name) {
        Optional<Species> speciesOptional = speciesRepository.findByName(name);
        if (speciesOptional.isPresent()) {
            return buildSpeciesDto(speciesOptional.get());
        }
        else {
            List<Species> results = speciesRepository.findByNameStartingWith(name);
            if (!results.isEmpty()) {
                return buildSpeciesDto(results.get(0));
            }
            else return null;
        }
    }

    public SpeciesDto buildSpeciesDto(Species species) {
        if (species != null) {
            SpeciesDto speciesDto = new SpeciesDto(species);
            int dbid = species.getDbid();
            speciesDto.setSpeciesAttacks(speciesAttackService.findBySpeciesDbid(dbid));
            speciesDto.setSpeciesAbilities(speciesAbilityService.findBySpeciesDbid(dbid));
            speciesDto.setCosmeticForms(cosmeticFormService.findBySpeciesDbid(dbid));

            List<SpeciesDto> speciesAtPrevDex = findByDexno(getPrevDex(species.getDexno()));
            if (speciesAtPrevDex != null && !speciesAtPrevDex.isEmpty()) {
                speciesDto.setPrevSpecies(buildSpeciesPageTabDto(speciesAtPrevDex.get(0)));
            }

            List<SpeciesDto> speciesAtNextDex = findByDexno(getNextDex(species.getDexno()));
            if (speciesAtNextDex != null && !speciesAtNextDex.isEmpty()) {
                speciesDto.setNextSpecies(buildSpeciesPageTabDto(speciesAtNextDex.get(0)));
            }
            /*speciesDto.setAlteredForms(buildAlteredFormDtoList(species));
            speciesDto.setAlteredFormMethod(alteredFormMethodService.findByBaseDex(species.getDexno());*/

            return speciesDto;
        }
        else return new SpeciesDto();
    }

    public int getNextDex(int dexno) {
        int dexBase0 = dexno - 1;
        return (dexBase0 + 1) % AppConfig.NUM_SPECIES + 1;
    }

    public int getPrevDex(int dexno) {
        int dexBase0 = dexno - 1;
        return (dexBase0 + AppConfig.NUM_SPECIES - 1) % AppConfig.NUM_SPECIES + 1;
    }

    public SpeciesPageTabDto buildSpeciesPageTabDto(SpeciesDto species) {
        return new SpeciesPageTabDto(species);
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
