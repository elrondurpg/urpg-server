package com.pokemonurpg.service;

import com.pokemonurpg.AppConfig;
import com.pokemonurpg.dto.CosmeticFormDto;
import com.pokemonurpg.dto.SpeciesAttackDto;
import com.pokemonurpg.dto.species.AlteredFormDto;
import com.pokemonurpg.dto.species.EvolutionFamilyMemberDto;
import com.pokemonurpg.dto.species.SpeciesDto;
import com.pokemonurpg.dto.species.SpeciesPageTabDto;
import com.pokemonurpg.object.*;
import com.pokemonurpg.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpeciesService {

    private SpeciesRepository speciesRepository;

    private SpeciesAttackService speciesAttackService;

    private SpeciesAbilityService speciesAbilityService;

    private AlteredFormMethodService alteredFormMethodService;
    private CosmeticFormService cosmeticFormService;

    private EvolutionService evolutionService;
    private MegaEvolutionService megaEvolutionService;

    private TypeMatchupService typeMatchupService;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository, SpeciesAttackService speciesAttackService,
                          SpeciesAbilityService speciesAbilityService,
                          AlteredFormMethodService alteredFormMethodService, CosmeticFormService cosmeticFormService,
                          @Lazy EvolutionService evolutionService, @Lazy MegaEvolutionService megaEvolutionService,
                          TypeMatchupService typeMatchupService) {
        this.speciesRepository = speciesRepository;
        this.speciesAttackService = speciesAttackService;
        this.speciesAbilityService = speciesAbilityService;
        this.alteredFormMethodService = alteredFormMethodService;
        this.cosmeticFormService = cosmeticFormService;
        this.evolutionService = evolutionService;
        this.megaEvolutionService = megaEvolutionService;
        this.typeMatchupService = typeMatchupService;
    }

    public List<Species> findAll() {
        return speciesRepository.findAll();
    }

    public List<Integer> findAllSpeciesDbidsByDexno(Integer dexno) {
        List<Species> speciesList = speciesRepository.findByDexno(dexno);
        List<Integer> dbidList = new ArrayList<>();
        if (speciesList != null && !speciesList.isEmpty()) {
            for (Species species : speciesList) {
                dbidList.add(species.getDbid());
            }
            return dbidList;
        }
        return Collections.emptyList();
    }

    Species findByDbid(int dbid) {
        return speciesRepository.findByDbid(dbid);
    }

    public SpeciesDto findByDexno(Integer dexno) {
        List<Species> results = speciesRepository.findByDexno(dexno);
        if (results != null && !results.isEmpty()) {
            return buildSpeciesDto(results.get(0));
        }
        else return null;
    }

    public SpeciesDto findByName(String name) {
        Species species = speciesRepository.findByName(name);
        if (species != null) {
            return buildSpeciesDto(species);
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

            int dexno = species.getDexno();
            List<Species> speciesAtPrevDex = speciesRepository.findByDexno(getPrevDex(dexno));
            if (speciesAtPrevDex != null && !speciesAtPrevDex.isEmpty()) {
                speciesDto.setPrevSpecies(buildSpeciesPageTabDto(speciesAtPrevDex.get(0)));
            }

            List<Species> speciesAtNextDex = speciesRepository.findByDexno(getNextDex(dexno));
            if (speciesAtNextDex != null && !speciesAtNextDex.isEmpty()) {
                speciesDto.setNextSpecies(buildSpeciesPageTabDto(speciesAtNextDex.get(0)));
            }

            List<Species> speciesAtThisDex = speciesRepository.findByDexno(species.getDexno());
            List<AlteredFormDto> alteredFormDtos = buildAlteredFormList(speciesAtThisDex);
            speciesDto.setAlteredForms(alteredFormDtos);

            speciesDto.setUniqueMoves(buildUniqueMoveList(alteredFormDtos));
            speciesDto.setAlteredFormMethod(alteredFormMethodService.findByDexno(species.getDexno()));

            speciesDto.setCosmeticForms(buildCosmeticForms(species, alteredFormDtos));

            speciesDto.setEvolutionFamily(buildEvolutionFamily(species));

            speciesDto.setMegaEvolutions(megaEvolutionService.findByOriginalDbid(species.getDbid()));

            speciesDto.setTypeMatchups(typeMatchupService.findTypeMatchupsBySpecies(species));
            return speciesDto;
        }
        else return new SpeciesDto();
    }

    public int getNextDex(int dexno) {
        return dexno % AppConfig.NUM_SPECIES + 1;
    }

    public int getPrevDex(int dexno) {
        return (dexno + AppConfig.NUM_SPECIES - 2) % AppConfig.NUM_SPECIES + 1;
    }

    public SpeciesPageTabDto buildSpeciesPageTabDto(Species species) {
        return new SpeciesPageTabDto(species);
    }

    public AlteredFormDto buildAlteredFormDto(Species species) {
        return new AlteredFormDto(species);
    }

    public List<AlteredFormDto> buildAlteredFormList(List<Species> speciesList) {
        List<AlteredFormDto> dtos = new ArrayList<>();

        if (speciesList.size() > 1) {
            for (Species species : speciesList) {
                dtos.add(buildAlteredFormDto(species));
            }
        }

        return dtos;
    }

    public List<String> buildUniqueMoveList(List<AlteredFormDto> alteredFormDtoList) {
        List<String> uniqueMoves = new ArrayList<>();
        HashMap<String, String> nonUniqueMovesAndMethods = new HashMap<>();

        List<HashMap<String, String>> allFormAttackMethods = new ArrayList<>();

        if (alteredFormDtoList != null && alteredFormDtoList.size() > 1) {
            int index = 0;
            for (AlteredFormDto form : alteredFormDtoList) {
                HashMap<String, String> formAttackMethods = new HashMap<>();
                allFormAttackMethods.add(formAttackMethods);

                int dbid = form.getDbid();
                List<SpeciesAttackDto> attacks = speciesAttackService.findBySpeciesDbid(dbid);
                for (SpeciesAttackDto attack : attacks) {
                    String attackName = attack.getName();
                    String attackMethod = attack.getMethod();
                    formAttackMethods.put(attackName, attackMethod);
                    if (!uniqueMoves.contains(attackName) && !nonUniqueMovesAndMethods.containsKey(attackName) && index == 0) {
                        nonUniqueMovesAndMethods.put(attackName, attackMethod);
                    }
                    else if (!uniqueMoves.contains(attackName) && !nonUniqueMovesAndMethods.containsKey(attackName) && index != 0) {
                        uniqueMoves.add(attackName);
                    }
                    else if (nonUniqueMovesAndMethods.containsKey(attackName) && !Objects.equals(attackMethod, nonUniqueMovesAndMethods.get(attackName))) {
                        uniqueMoves.add(attackName);
                        nonUniqueMovesAndMethods.remove(attackName);
                    }
                    else if (nonUniqueMovesAndMethods.containsKey(attackName) && index == alteredFormDtoList.size() - 1) {
                        nonUniqueMovesAndMethods.remove(attackName);
                    }
                }
                index++;
            }

            for (String attackName : nonUniqueMovesAndMethods.keySet()) {
                uniqueMoves.add(attackName);
            }

            Collections.sort(uniqueMoves);

            index = 0;
            for (AlteredFormDto form : alteredFormDtoList) {
                HashMap<String, String> formUniqueAttacks = new HashMap<>();
                for (String attack : uniqueMoves) {
                    String method = allFormAttackMethods.get(index).get(attack);
                    formUniqueAttacks.put(attack, method);
                }
                form.setUniqueAttacks(formUniqueAttacks);
                index++;
            }
        }
        return uniqueMoves;
    }

    public List<CosmeticFormDto> buildCosmeticForms(Species species, List<AlteredFormDto> alteredForms) {
        List<CosmeticFormDto> cosmeticFormDtos = new ArrayList<>();
        if (species != null) {
            for (CosmeticFormDto cosmeticFormDto : cosmeticFormService.findBySpeciesDbid(species.getDbid())) {
                if (!cosmeticFormDtos.contains(cosmeticFormDto)) {
                    cosmeticFormDtos.add(cosmeticFormDto);
                }
            }

            if (alteredForms != null) {
                for (AlteredFormDto form : alteredForms) {
                    for (CosmeticFormDto cosmeticFormDto : cosmeticFormService.findBySpeciesDbid(form.getDbid())) {
                        if (!cosmeticFormDtos.contains(cosmeticFormDto)) {
                            cosmeticFormDtos.add(cosmeticFormDto);
                        }
                    }
                }
            }
            return cosmeticFormDtos;
        }
        else return Collections.emptyList();
    }

    public List<List<EvolutionFamilyMemberDto>> buildEvolutionFamily(Species species) {
        List<List<EvolutionFamilyMemberDto>> evolutionFamily = new ArrayList<>();

        List<EvolutionFamilyMemberDto> basicStage = Arrays.asList(findBasicForm(species));
        evolutionFamily.add(basicStage);

        List<EvolutionFamilyMemberDto> firstStage = new ArrayList<>();
        for (EvolutionFamilyMemberDto member : basicStage) {
            firstStage.addAll(evolutionService.findEvolutionsByPreEvolutionDbid(member.getDbid()));
        }
        evolutionFamily.add(firstStage);

        List<EvolutionFamilyMemberDto> secondStage = new ArrayList<>();
        for (EvolutionFamilyMemberDto member : firstStage) {
            secondStage.addAll(evolutionService.findEvolutionsByPreEvolutionDbid(member.getDbid()));
        }
        evolutionFamily.add(secondStage);

        return evolutionFamily;
    }

    public EvolutionFamilyMemberDto findBasicForm(Species species) throws IllegalStateException {
        if (species != null) {
            int dbidOfSpeciesToCheck = species.getDbid();
            int prevoDbid;

            do {
                prevoDbid = evolutionService.getPreEvolutionDbid(dbidOfSpeciesToCheck);
                if (prevoDbid != -1 && prevoDbid != 0) {
                    dbidOfSpeciesToCheck = prevoDbid;
                }
            } while (prevoDbid != -1 && prevoDbid != 0);

            if (dbidOfSpeciesToCheck != species.getDbid()) {
                Species prevo = speciesRepository.findByDbid(dbidOfSpeciesToCheck);
                if (prevo != null) {
                    return new EvolutionFamilyMemberDto(prevo, null);
                } else
                    throw new IllegalStateException("Basic form with DBID " + dbidOfSpeciesToCheck + " does not exist!");
            } else {
                return new EvolutionFamilyMemberDto(species, null);
            }
        }
        else return new EvolutionFamilyMemberDto();
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
