package com.pokemonurpg.species.service;

import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.species.input.CosmeticFormInputDto;
import com.pokemonurpg.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.input.SpeciesInputDto;
import com.pokemonurpg.species.repository.SpeciesRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesService implements NamedObjectService<Species> {

    @Resource
    private SpeciesRepository speciesRepository;

    @Resource
    private TypeService typeService;

    @Resource
    private StoryRankService storyRankService;

    @Resource
    private ArtRankService artRankService;

    @Resource
    private ParkRankService parkRankService;

    @Resource
    private ParkLocationService parkLocationService;

    @Resource
    private SpeciesAttackService speciesAttackService;

    @Resource
    private SpeciesAbilityService speciesAbilityService;

    @Resource
    private CosmeticFormService cosmeticFormService;

    public List<String> findAllNames() {
        return speciesRepository.findAllNames();
    }

    public List<String> findAllStarterNames() {
        return speciesRepository.findAllStarterNames();
    }

    public Species findByDbid(int dbid) {
        return speciesRepository.findByDbid(dbid);
    }

    public List<Species> findByDexno(int dexno) { return speciesRepository.findByDexno(dexno); }

    public Species findFirstByDexno(int dexno) { return speciesRepository.findFirstByDexno(dexno); }

    public Integer findMaxDexno() { return speciesRepository.findMaxDexno(); }

    public Species findByName(String name) {
        Species species = findByNameExact(name);
        if (species == null && name != null) {
            return speciesRepository.findFirstByNameStartingWith(name);
        }
        else return species;
    }

    @Override
    public Species findByNameExact(String name) {
        return speciesRepository.findByName(name);
    }

    /*public List<String> findByRank(String rankName) {
        List<String> speciesNames = new ArrayList<>();
        List<Species> speciesList = null;

        StoryRank rank = storyRankRepository.findByNameExact(rankName);
        if (rank != null) {
            speciesList = speciesRepository.findByStoryRank(rank);
        }
        else {
            ParkRank parkRank = parkRankRepository.findByNameExact(rankName);
            if (parkRank != null) {
                speciesList = speciesRepository.findByParkRank(parkRank);
            }
        }

        if (speciesList != null) {
            for (Species species : speciesList) {
                speciesNames.add(species.getName());
            }
        }

        return speciesNames;
    }*/

    public List<Species> findByPreEvolution(Species species) {
        return speciesRepository.findByPreEvolution(species);
    }

    public List<Species> findByPreMega(Species species) { return speciesRepository.findByPreMega(species); }

    public Species create(SpeciesInputDto input) {
        Species species = new Species(input);

        updateEmbeddedValues(species, input);
        speciesRepository.save(species);

        updateAssociatedValues(species, input);

        return species;
    }

    public Species update(SpeciesInputDto input, int dbid) {
        Species species = speciesRepository.findByDbid(dbid);
        if (species != null) {
            species.update(input);

            updateEmbeddedValues(species, input);
            speciesRepository.save(species);

            updateAssociatedValues(species, input);
        }
        return species;
    }

    void updateEmbeddedValues(Species species, SpeciesInputDto input) {
        species.setType1(typeService.findByName(input.getType1()));
        species.setType2(typeService.findByName(input.getType2()));
        species.setStoryRank(storyRankService.findByName(input.getStoryRank()));
        species.setArtRank(artRankService.findByName(input.getArtRank()));
        species.setParkRank(parkRankService.findByName(input.getParkRank()));
        species.setParkLocation(parkLocationService.findByName(input.getParkLocation()));
        species.setPreMega(findByName(input.getPreMega()));
        species.setPreEvolution(findByName(input.getPreEvolution()));
    }

    void updateAssociatedValues(Species species, SpeciesInputDto input) {
        updateSpeciesAttacks(species, input);
        updateSpeciesAbilities(species, input);
        updateCosmeticForms(input);
    }

    private void updateSpeciesAttacks(Species species, SpeciesInputDto input) {
        List<SpeciesAttackInputDto> attacks = input.getAttacks();
        for (SpeciesAttackInputDto attack : attacks) {
            speciesAttackService.update(species, attack);
        }
    }

    private void updateSpeciesAbilities(Species species, SpeciesInputDto input) {
        List<SpeciesAbilityInputDto> abilities = input.getAbilities();
        for (SpeciesAbilityInputDto ability : abilities) {
            speciesAbilityService.update(species, ability);
        }
    }

    private void updateCosmeticForms(SpeciesInputDto input) {
        List<CosmeticFormInputDto> forms = input.getCosmeticForms();
        for (CosmeticFormInputDto form : forms) {
            cosmeticFormService.update(form);
        }
    }
}
