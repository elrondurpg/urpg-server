package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.dto.species.input.*;
import com.pokemonurpg.dto.species.response.CosmeticFormDto;
import com.pokemonurpg.dto.species.response.SpeciesAttackDto;
import com.pokemonurpg.dto.species.response.*;
import com.pokemonurpg.object.pokemon.Species;
import com.pokemonurpg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

import java.util.*;

@Service
public class SpeciesService {

    private SpeciesRepository speciesRepository;

    private SpeciesAttackService speciesAttackService;
    private AttackRepository attackRepository;

    private SpeciesAbilityService speciesAbilityService;
    private AbilityRepository abilityRepository;

    private AlteredFormMethodService alteredFormMethodService;
    private CosmeticFormService cosmeticFormService;

    private EvolutionService evolutionService;

    private MegaEvolutionService megaEvolutionService;

    private TypeMatchupService typeMatchupService;

    private TypeRepository typeRepository;
    private StoryRankRepository storyRankRepository;
    private ArtRankRepository artRankRepository;
    private ParkRankRepository parkRankRepository;
    private ParkLocationRepository parkLocationRepository;

    @Autowired
    public SpeciesService(SpeciesRepository speciesRepository, SpeciesAttackService speciesAttackService, AttackRepository attackRepository,
                          SpeciesAbilityService speciesAbilityService, AbilityRepository abilityRepository,
                          AlteredFormMethodService alteredFormMethodService, CosmeticFormService cosmeticFormService,
                          EvolutionService evolutionService, MegaEvolutionService megaEvolutionService,
                          TypeMatchupService typeMatchupService, TypeRepository typeRepository,
                          StoryRankRepository storyRankRepository, ArtRankRepository artRankRepository,
                          ParkRankRepository parkRankRepository, ParkLocationRepository parkLocationRepository) {
        this.speciesRepository = speciesRepository;
        this.speciesAttackService = speciesAttackService;
        this.attackRepository = attackRepository;
        this.speciesAbilityService = speciesAbilityService;
        this.abilityRepository = abilityRepository;
        this.alteredFormMethodService = alteredFormMethodService;
        this.cosmeticFormService = cosmeticFormService;
        this.evolutionService = evolutionService;
        this.megaEvolutionService = megaEvolutionService;
        this.typeMatchupService = typeMatchupService;
        this.typeRepository = typeRepository;
        this.storyRankRepository = storyRankRepository;
        this.artRankRepository = artRankRepository;
        this.parkRankRepository = parkRankRepository;
        this.parkLocationRepository = parkLocationRepository;
    }

    public List<Object> findAll() {
        return speciesRepository.findAllNames();
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
            speciesDto.setAttacks(speciesAttackService.findBySpeciesDbid(dbid));

            List<SpeciesAbilityDto> abilities = speciesAbilityService.findBySpeciesDbid(dbid);
            Collections.sort(abilities);
            speciesDto.setAbilities(abilities);

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
            String alteredFormMethod = alteredFormMethodService.findByDexno(species.getDexno());
            List<AlteredFormDto> alteredFormDtos = buildAlteredFormList(speciesAtThisDex, alteredFormMethod);
            alteredFormDtos.addAll(buildCosmeticForms(species, alteredFormDtos, alteredFormMethod));
            speciesDto.setAlteredForms(alteredFormDtos);
            speciesDto.setUniqueMoves(buildUniqueMoveList(alteredFormDtos));

            speciesDto.setEvolutionFamily(buildEvolutionFamily(species));
            speciesDto.setEvolvesFrom(evolutionService.findByEvolutionDbid(species.getDbid()));

            speciesDto.setMegaEvolutions(megaEvolutionService.findByOriginalDbid(species.getDbid()));
            speciesDto.setMegaEvolvesFrom(megaEvolutionService.findByMegaDbid(species.getDbid()));

            speciesDto.setTypeMatchups(typeMatchupService.findTypeMatchupsBySpecies(species));
            for (MegaEvolutionDto megaDto : speciesDto.getMegaEvolutions()) {
                megaDto.setTypeMatchups(typeMatchupService.findTypeMatchupsBySpeciesTypes(megaDto.getType1(), megaDto.getType2()));
            }

            return speciesDto;
        }
        else return new SpeciesDto();
    }

    public int getNextDex(int dexno) {
        int maxDex = speciesRepository.findMaxDexno();
        return dexno % maxDex + 1;
    }

    public int getPrevDex(int dexno) {
        int maxDex = speciesRepository.findMaxDexno();
        return (dexno + maxDex - 2) % maxDex + 1;
    }

    public SpeciesPageTabDto buildSpeciesPageTabDto(Species species) {
        return new SpeciesPageTabDto(species);
    }

    public AlteredFormDto buildAlteredFormDto(Species species) {
        return new AlteredFormDto(species);
    }

    public List<AlteredFormDto> buildAlteredFormList(List<Species> speciesList, String method) {
        List<AlteredFormDto> dtos = new ArrayList<>();

        if (speciesList.size() > 1) {
            for (Species species : speciesList) {
                if (!megaEvolutionService.isMegaEvolution(species.getDbid())) {
                    AlteredFormDto dto = buildAlteredFormDto(species);
                    dto.setMethod(method);
                    List<SpeciesAbilityDto> abilities = speciesAbilityService.findBySpeciesDbid(dto.getDbid());
                    Collections.sort(abilities);
                    dto.setAbilities(abilities);
                    dtos.add(dto);
                }
            }
        }

        if (dtos.size() == 1) {
            return Collections.emptyList();
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

    public List<AlteredFormDto> buildCosmeticForms(Species species, List<AlteredFormDto> alteredForms, String method) {
        List<AlteredFormDto> cosmeticFormDtos = new ArrayList<>();
        if (species != null) {
            boolean noAlteredForms = alteredForms == null || alteredForms.isEmpty();
            if (noAlteredForms) {
                AlteredFormDto dto = new AlteredFormDto(species);
                List<SpeciesAbilityDto> abilities = speciesAbilityService.findBySpeciesDbid(dto.getDbid());
                Collections.sort(abilities);
                dto.setAbilities(abilities);
                cosmeticFormDtos.add(dto);
            }

            for (CosmeticFormDto cosmeticFormDto : cosmeticFormService.findBySpeciesDbid(species.getDbid())) {
                AlteredFormDto dto = new AlteredFormDto(species, cosmeticFormDto);
                List<SpeciesAbilityDto> abilities = speciesAbilityService.findBySpeciesDbid(dto.getDbid());
                Collections.sort(abilities);
                dto.setAbilities(abilities);
                dto.setCosmetic(true);
                cosmeticFormDtos.add(dto);
            }

            if (alteredForms != null) {
                for (AlteredFormDto form : alteredForms) {
                    if (form.getDbid() != species.getDbid()) {
                        for (CosmeticFormDto cosmeticFormDto : cosmeticFormService.findBySpeciesDbid(form.getDbid())) {
                            AlteredFormDto dto = new AlteredFormDto(form, cosmeticFormDto);
                            List<SpeciesAbilityDto> abilities = speciesAbilityService.findBySpeciesDbid(dto.getDbid());
                            Collections.sort(abilities);
                            dto.setAbilities(abilities);
                            dto.setCosmetic(true);
                            cosmeticFormDtos.add(dto);
                        }
                    }
                }
            }

            if (cosmeticFormDtos.size() == 1 && noAlteredForms)
                return Collections.emptyList();

            if (cosmeticFormDtos.size() > 1) {
                if (cosmeticFormDtos.get(0).getMethod() == null) {
                    for (AlteredFormDto dto : cosmeticFormDtos) {
                        if (dto.getMethod() != null) {
                            cosmeticFormDtos.get(0).setMethod(dto.getMethod());
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

    public Errors createSpecies(SpeciesInputDto input) {
        Errors errors = validateSpeciesCreate(input);

        if (!errors.hasErrors()) {
            Species newSpecies = new Species(input);
            newSpecies.setType1(typeRepository.findByName(input.getType1()));
            newSpecies.setType2(typeRepository.findByName(input.getType2()));

            if (input.getStoryRank() == null || input.getStoryRank().equals("")) {
                input.setStoryRank("-");
            }
            newSpecies.setStoryRank(storyRankRepository.findByName(input.getStoryRank()));

            if (input.getArtRank() == null || input.getArtRank().equals("")) {
                input.setArtRank("-");
            }
            newSpecies.setArtRank(artRankRepository.findByName(input.getArtRank()));

            if (input.getParkRank() == null || input.getParkRank().equals("")) {
                input.setParkRank("-");
            }
            newSpecies.setParkRank(parkRankRepository.findByName(input.getParkRank()));

            if (input.getParkLocation() == null || input.getParkLocation().equals("")) {
                input.setParkLocation("-");
            }
            newSpecies.setParkLocation(parkLocationRepository.findByName(input.getParkLocation()));
            speciesRepository.save(newSpecies);

            Species savedSpecies = speciesRepository.findByName(input.getName());
            int dbid = savedSpecies.getDbid();

            speciesAttackService.createAll(dbid, input.getAttacks());
            speciesAbilityService.createAll(dbid, input.getAbilities());
            alteredFormMethodService.create(dbid, input.getAlteredFormMethod());
            cosmeticFormService.createAll(dbid, input.getCosmeticForms(), input.getAlteredFormMethod());
            evolutionService.create(dbid, input.getEvolvesFrom());
            megaEvolutionService.create(dbid, input.getMegaEvolvesFrom());
        }

        return errors;
    }

    public Errors updateSpecies(SpeciesInputDto input) {
        Errors errors = validateSpeciesUpdate(input);

        if (!errors.hasErrors()) {
            Species existingSpecies = speciesRepository.findByName(input.getName());
            if (input.getDexno() != null) {
                existingSpecies.setDexno(input.getDexno());
            }
            if (input.getName() != null) {
                existingSpecies.setName(input.getName());
            }
            if (input.getClassification() != null) {
                existingSpecies.setClassification(input.getClassification());
            }
            if (input.getHp() != null) {
                existingSpecies.setHp(input.getHp());
            }
            if (input.getHp() != null) {
                existingSpecies.setHp(input.getHp());
            }
            if (input.getAttack() != null) {
                existingSpecies.setAttack(input.getAttack());
            }
            if (input.getDefense() != null) {
                existingSpecies.setDefense(input.getDefense());
            }
            if (input.getSpecialAttack() != null) {
                existingSpecies.setSpecialAttack(input.getSpecialAttack());
            }
            if (input.getSpecialDefense() != null) {
                existingSpecies.setSpecialDefense(input.getSpecialDefense());
            }
            if (input.getSpeed() != null) {
                existingSpecies.setSpeed(input.getSpeed());
            }
            if (input.getHeight() != null) {
                existingSpecies.setHeight(input.getHeight());
            }
            if (input.getWeight() != null) {
                existingSpecies.setWeight(input.getWeight());
            }
            if (input.isMaleAllowed() != null) {
                existingSpecies.setMaleAllowed(input.isMaleAllowed());
            }
            if (input.isFemaleAllowed() != null) {
                existingSpecies.setFemaleAllowed(input.isFemaleAllowed());
            }
            if (input.getPokemart() != null) {
                existingSpecies.setPokemart(input.getPokemart());
            }
            if (input.getContestCredits() != null) {
                existingSpecies.setContestCredits(input.getContestCredits());
            }
            if (input.getDisplayName() != null) {
                existingSpecies.setDisplayName(input.getDisplayName());
            }
            if (input.getFormName() != null) {
                existingSpecies.setFormName(input.getFormName());
            }
            if (input.getType1() != null) {
                existingSpecies.setType1(typeRepository.findByName(input.getType1()));
            }
            if (input.getType2() != null) {
                existingSpecies.setType2(typeRepository.findByName(input.getType2()));
            }
            if (input.getStoryRank() != null) {
                existingSpecies.setStoryRank(storyRankRepository.findByName(input.getStoryRank()));
            }
            if (input.getArtRank() != null) {
                existingSpecies.setArtRank(artRankRepository.findByName(input.getArtRank()));
            }
            if (input.getParkRank() != null) {
                existingSpecies.setParkRank(parkRankRepository.findByName(input.getParkRank()));
            }
            if (input.getParkLocation() != null) {
                existingSpecies.setParkLocation(parkLocationRepository.findByName(input.getParkLocation()));
            }
            speciesRepository.save(existingSpecies);

            int dbid = existingSpecies.getDbid();

            speciesAttackService.updateAll(dbid, input.getAttacks());
            speciesAbilityService.updateAll(dbid, input.getAbilities());
            alteredFormMethodService.update(existingSpecies.getDexno(), input.getAlteredFormMethod());
            cosmeticFormService.updateAll(dbid, input.getCosmeticForms(), input.getAlteredFormMethod());
            evolutionService.update(dbid, input.getEvolvesFrom());
            megaEvolutionService.update(dbid, input.getMegaEvolvesFrom());
        }

        return errors;
    }

    public Errors validateSpeciesCreate(SpeciesInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Species existingRecord = speciesRepository.findByName(input.getName());
        if (existingRecord == null) {

            if (input.getDexno() == null || input.getDexno() <= 0) {
                errors.reject("Dex No. " + input.getDexno() + " is invalid.");
            }

            if (input.getName() == null || input.getName().length() < 3 || input.getName().length() > 21) {
                errors.reject("Name " + input.getName() + " is invalid.");
            }

            if (input.getDisplayName() != null && (input.getDisplayName().length() < 3 || input.getDisplayName().length() > 20)) {
                errors.reject("Display name " + input.getDisplayName() + " is invalid.");
            }

            if (input.getFormName() != null && (input.getFormName().length() < 3 || input.getFormName().length() > 20)) {
                errors.reject("Form name " + input.getFormName() + " is invalid.");
            }

            if (input.getType1() == null || input.getType1().equalsIgnoreCase("NONE")) {
                errors.reject("Type 1 cannot be null!");
            }
            else if (typeRepository.findByName(input.getType1()) == null) {
                errors.reject("Type 1: " + input.getType1() + " is invalid.");
            }

            if (input.getType2() != null && (typeRepository.findByName(input.getType2()) == null || input.getType2().equalsIgnoreCase(input.getType1()))) {
                errors.reject("Type 2: " + input.getType2() + " is invalid.");
            }

            if (input.getClassification() != null && (input.getClassification().length() < 3 || input.getClassification().length() > 20)){
                errors.reject("Classification " + input.getClassification() + " is invalid.");
            }

            if (input.getHp() == null || input.getHp() < 0 || input.getHp() > 1000) {
                errors.reject("HP " + input.getHp() + " is invalid.");
            }

            if (input.getAttack() == null || input.getAttack() < 0 || input.getAttack() > 1000) {
                errors.reject("Attack " + input.getAttack() + " is invalid.");
            }

            if (input.getDefense() == null || input.getDefense() < 0 || input.getDefense() > 1000) {
                errors.reject("Defense " + input.getDefense() + " is invalid.");
            }

            if (input.getSpecialAttack() == null || input.getSpecialAttack() < 0 || input.getSpecialAttack() > 1000) {
                errors.reject("Special Attack " + input.getSpecialAttack() + " is invalid.");
            }

            if (input.getSpecialDefense() == null || input.getSpecialDefense() < 0 || input.getSpecialDefense() > 1000) {
                errors.reject("Special Defense " + input.getSpecialDefense() + " is invalid.");
            }

            if (input.getSpeed() == null || input.getSpeed() < 0 || input.getSpeed() > 1000) {
                errors.reject("Speed " + input.getSpeed() + " is invalid.");
            }

            if (input.getHeight() == null || input.getHeight() < 0 || input.getHeight() > 1000000) {
                errors.reject("Height " + input.getHeight() + " is invalid.");
            }

            if (input.getWeight() == null || input.getWeight() < 0 || input.getWeight() > 1000000) {
                errors.reject("Weight " + input.getWeight() + " is invalid.");
            }

            if (input.isMaleAllowed() == null) {
                errors.reject("maleAllowed cannot be null!");
            }

            if (input.isFemaleAllowed() == null) {
                errors.reject("femaleAllowed cannot be null!");
            }

            if (input.getPokemart() != null && (input.getPokemart() < 0 || input.getPokemart() > 100000)) {
                errors.reject("Pokemart value " + input.getPokemart() + " is invalid.");
            }

            if (input.getContestCredits() != null && (input.getContestCredits() < 0 || input.getContestCredits() > 100000)) {
                errors.reject("Contest credit value " + input.getContestCredits() + " is invalid.");
            }

            if (input.getStoryRank() != null && storyRankRepository.findByName(input.getStoryRank()) == null) {
                errors.reject("Story Rank " + input.getStoryRank() + " is invalid.");
            }

            if (input.getArtRank() != null && artRankRepository.findByName(input.getArtRank()) == null) {
                errors.reject("Art Rank " + input.getArtRank() + " is invalid.");
            }

            if (input.getParkRank() != null && parkRankRepository.findByName(input.getParkRank()) == null) {
                errors.reject("Park Rank " + input.getParkRank() + " is invalid.");
            }

            if (input.getParkLocation() != null && parkLocationRepository.findByName(input.getParkLocation()) == null) {
                errors.reject("Park Location " + input.getParkLocation() + " is invalid.");
            }

            if (input.getAttacks() != null) {
                for (SpeciesAttackInputDto speciesAttackInputDto : input.getAttacks()) {
                    if (speciesAttackInputDto.getName() == null || attackRepository.findByName(speciesAttackInputDto.getName()) == null) {
                        errors.reject("Attack name " + speciesAttackInputDto.getName() + " is invalid.");
                    }
                    if (speciesAttackInputDto.getMethod() == null) {
                        errors.reject("Attack learn method " + speciesAttackInputDto.getMethod() + " for " + speciesAttackInputDto.getName() + " is invalid.");
                    }
                }
            }

            if (input.getAbilities() != null) {
                for (SpeciesAbilityInputDto speciesAbilityInputDto : input.getAbilities()) {
                    if (speciesAbilityInputDto.getName() == null || abilityRepository.findByName((speciesAbilityInputDto.getName())) == null) {
                        errors.reject("Ability name " + speciesAbilityInputDto.getName() + " is invalid.");
                    }
                    if (speciesAbilityInputDto.isHidden() == null) {
                        errors.reject("Hidden property of " + speciesAbilityInputDto.getName() + " cannot be null!");
                    }
                }
            }

            if (input.getCosmeticForms() != null) {
                for (CosmeticFormInputDto cosmeticFormDto : input.getCosmeticForms()) {
                    if (cosmeticFormDto.getName() == null || cosmeticFormDto.getName().length() > 20) {
                        errors.reject("Cosmetic form name " + cosmeticFormDto.getName() + " is invalid.");
                    }
                    if (cosmeticFormDto.getFormName() == null || cosmeticFormDto.getFormName().length() > 20) {
                        errors.reject("Cosmetic form display name " + cosmeticFormDto.getFormName() + " is invalid.");
                    }
                    if (cosmeticFormDto.getMethod() == null || cosmeticFormDto.getMethod().length() > 110) {
                        errors.reject("Cosmetic form method " + cosmeticFormDto.getMethod() + " is invalid.");
                    }
                }
            }

            if (input.getAlteredFormMethod() != null && input.getAlteredFormMethod().length() > 100) {
                errors.reject("Altered form method " + input.getAlteredFormMethod() + " is invalid.");
            }

            if (input.getEvolvesFrom() != null) {
                EvolutionInputDto evolutionInputDto = input.getEvolvesFrom();
                if (evolutionInputDto.getName() != null) {
                    if (speciesRepository.findByName(evolutionInputDto.getName()) == null) {
                        errors.reject("Pre-evolved form " + evolutionInputDto.getName() + " is not a real Pokemon.");
                    }
                    if (evolutionInputDto.getMethod() != null && evolutionInputDto.getMethod().length() > 50) {
                        errors.reject("Evolution method " + evolutionInputDto.getMethod() + " is invalid.");
                    }
                    if (evolutionInputDto.getNumBattles() != null && evolutionInputDto.getNumBattles() > 10) {
                        errors.reject("Evolution EXP requirement " + evolutionInputDto.getNumBattles() + " is invalid.");
                    }
                }
            }

            if (input.getMegaEvolvesFrom() != null) {
                MegaEvolutionInputDto megaEvolutionInputDto = input.getMegaEvolvesFrom();
                if (megaEvolutionInputDto.getName() != null) {
                    if (speciesRepository.findByName(megaEvolutionInputDto.getName()) == null) {
                        errors.reject("Pre-mega form " + megaEvolutionInputDto.getName() + " is not a real Pokemon.");
                    }
                    if (megaEvolutionInputDto.getMegaStone() == null || megaEvolutionInputDto.getMegaStone().length() > 50) {
                        errors.reject("Mega Stone " + megaEvolutionInputDto.getMegaStone() + " is invalid.");
                    }
                }
            }
        }
        else {
            errors.reject("Pokemon " + input.getName() + " already exists.");
        }

        return errors;
    }

    public Errors validateSpeciesUpdate(SpeciesInputDto input) {
        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "");

        Species existingRecord = speciesRepository.findByName(input.getName());
        if (existingRecord != null) {

            if (input.getDexno() != null && input.getDexno() <= 0) {
                errors.reject("Dex No. " + input.getDexno() + " is invalid.");
            }

            if (input.getName() != null && (input.getName().length() < 3 || input.getName().length() > 21)) {
                errors.reject("Name " + input.getName() + " is invalid.");
            }

            if (input.getDisplayName() != null && (input.getDisplayName().length() < 3 || input.getDisplayName().length() > 20)) {
                errors.reject("Display name " + input.getDisplayName() + " is invalid.");
            }

            if (input.getFormName() != null && (input.getFormName().length() < 3 || input.getFormName().length() > 20)) {
                errors.reject("Form name " + input.getFormName() + " is invalid.");
            }

            if (input.getType1() != null && (typeRepository.findByName(input.getType1()) == null || input.getType1().equalsIgnoreCase("NONE"))) {
                errors.reject("Type 1: " + input.getType1() + " is invalid.");
            }

            if (input.getType2() != null && (typeRepository.findByName(input.getType2()) == null || input.getType2().equalsIgnoreCase(input.getType1()))) {
                errors.reject("Type 2: " + input.getType2() + " is invalid.");
            }

            if (input.getClassification() != null && input.getClassification().length() > 20){
                errors.reject("Classification " + input.getClassification() + " is invalid.");
            }

            if (input.getHp() != null && (input.getHp() < 0 || input.getHp() > 1000)) {
                errors.reject("HP " + input.getHp() + " is invalid.");
            }

            if (input.getAttack() != null && (input.getAttack() < 0 || input.getAttack() > 1000)) {
                errors.reject("Attack " + input.getAttack() + " is invalid.");
            }

            if (input.getDefense() != null && (input.getDefense() < 0 || input.getDefense() > 1000)) {
                errors.reject("Defense " + input.getDefense() + " is invalid.");
            }

            if (input.getSpecialAttack() != null && (input.getSpecialAttack() < 0 || input.getSpecialAttack() > 1000)) {
                errors.reject("Special Attack " + input.getSpecialAttack() + " is invalid.");
            }

            if (input.getSpecialDefense() != null && (input.getSpecialDefense() < 0 || input.getSpecialDefense() > 1000)) {
                errors.reject("Special Defense " + input.getSpecialDefense() + " is invalid.");
            }

            if (input.getSpeed() != null && (input.getSpeed() < 0 || input.getSpeed() > 1000)) {
                errors.reject("Speed " + input.getSpeed() + " is invalid.");
            }

            if (input.getHeight() != null && (input.getHeight() < 0 || input.getHeight() > 1000000)) {
                errors.reject("Height " + input.getHeight() + " is invalid.");
            }

            if (input.getWeight() != null && (input.getWeight() < 0 || input.getWeight() > 1000000)) {
                errors.reject("Weight " + input.getWeight() + " is invalid.");
            }

            if (input.getPokemart() != null && (input.getPokemart() < 0 || input.getPokemart() > 100000)) {
                errors.reject("Pokemart value " + input.getPokemart() + " is invalid.");
            }

            if (input.getContestCredits() != null && (input.getContestCredits() < 0 || input.getContestCredits() > 100000)) {
                errors.reject("Contest credit value " + input.getContestCredits() + " is invalid.");
            }

            if (input.getStoryRank() != null && storyRankRepository.findByName(input.getStoryRank()) == null) {
                errors.reject("Story Rank " + input.getStoryRank() + " is invalid.");
            }

            if (input.getArtRank() != null && artRankRepository.findByName(input.getArtRank()) == null) {
                errors.reject("Art Rank " + input.getArtRank() + " is invalid.");
            }

            if (input.getParkRank() != null && parkRankRepository.findByName(input.getParkRank()) == null) {
                errors.reject("Park Rank " + input.getParkRank() + " is invalid.");
            }

            if (input.getParkLocation() != null && parkLocationRepository.findByName(input.getParkLocation()) == null) {
                errors.reject("Park Location " + input.getParkLocation() + " is invalid.");
            }

            ArrayList<String> attackMethods = new ArrayList<>();
            attackMethods.add("LEVEL-UP");
            attackMethods.add("BREEDING");
            attackMethods.add("TM");
            attackMethods.add("HM");
            attackMethods.add("MOVE TUTOR");
            attackMethods.add("SPECIAL");

            if (input.getAttacks() != null) {
                for (SpeciesAttackInputDto speciesAttackInputDto : input.getAttacks()) {
                    if (speciesAttackInputDto.getName() != null && attackRepository.findByName(speciesAttackInputDto.getName()) == null) {
                        errors.reject("Attack name " + speciesAttackInputDto.getName() + " is invalid.");
                    }
                    if (speciesAttackInputDto.getMethod() != null && !attackMethods.contains(speciesAttackInputDto.getMethod())) {
                        errors.reject("Attack learn method " + speciesAttackInputDto.getMethod() + " for " + speciesAttackInputDto.getName() + " is invalid.");
                    }
                }
            }

            if (input.getAbilities() != null) {
                for (SpeciesAbilityInputDto speciesAbilityInputDto : input.getAbilities()) {
                    if (speciesAbilityInputDto.getName() != null && abilityRepository.findByName((speciesAbilityInputDto.getName())) == null) {
                        errors.reject("Ability name " + speciesAbilityInputDto.getName() + " is invalid.");
                    }
                    if (speciesAbilityInputDto.isHidden() == null) {
                        errors.reject("Hidden property of " + speciesAbilityInputDto.getName() + " cannot be null!");
                    }
                }
            }

            if (input.getCosmeticForms() != null) {
                for (CosmeticFormInputDto cosmeticFormDto : input.getCosmeticForms()) {
                    if (cosmeticFormDto.getName() != null && cosmeticFormDto.getName().length() > 20) {
                        errors.reject("Cosmetic form name " + cosmeticFormDto.getName() + " is invalid.");
                    }
                    if (cosmeticFormDto.getFormName() != null && cosmeticFormDto.getFormName().length() > 20) {
                        errors.reject("Cosmetic form display name " + cosmeticFormDto.getFormName() + " is invalid.");
                    }
                    if (cosmeticFormDto.getMethod() != null && cosmeticFormDto.getMethod().length() > 110) {
                        errors.reject("Cosmetic form method " + cosmeticFormDto.getMethod() + " is invalid.");
                    }
                }
            }

            if (input.getAlteredFormMethod() != null && input.getAlteredFormMethod().length() > 100) {
                errors.reject("Altered form method " + input.getAlteredFormMethod() + " is invalid.");
            }

            if (input.getEvolvesFrom() != null) {
                EvolutionInputDto evolutionInputDto = input.getEvolvesFrom();
                if (evolutionInputDto.getName() != null && speciesRepository.findByName(evolutionInputDto.getName()) == null) {
                    errors.reject("Pre-evolved form " + evolutionInputDto.getName() + " is not a real Pokemon.");
                }
                if (evolutionInputDto.getMethod() != null && evolutionInputDto.getMethod().length() > 50) {
                    errors.reject("Evolution method " + evolutionInputDto.getMethod() + " is invalid.");
                }
                if (evolutionInputDto.getNumBattles() != null && evolutionInputDto.getNumBattles() > 10) {
                    errors.reject("Evolution EXP requirement " + evolutionInputDto.getNumBattles() + " is invalid.");
                }
            }

            if (input.getMegaEvolvesFrom() != null) {
                MegaEvolutionInputDto megaEvolutionInputDto = input.getMegaEvolvesFrom();
                if (megaEvolutionInputDto.getName() != null && speciesRepository.findByName(megaEvolutionInputDto.getName()) == null) {
                    errors.reject("Pre-mega form " + megaEvolutionInputDto.getName() + " is not a real Pokemon.");
                }
                if (megaEvolutionInputDto.getMegaStone() != null && megaEvolutionInputDto.getMegaStone().length() > 50) {
                    errors.reject("Mega Stone " + megaEvolutionInputDto.getMegaStone() + " is invalid.");
                }
            }
        }
        else {
            errors.reject("Pokemon " + input.getName() + " doesn't exist.");
        }

        return errors;
    }
}
