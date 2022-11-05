package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesGetParams;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.service.ConfigurationService;
import com.pokemonurpg.core.service.NamedObjectService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpeciesService extends ConfigurationService<Species, SpeciesGetParams, SpeciesRepository> implements NamedObjectService<Species> {

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

    public List<String> findAllNames(Boolean ownable) {
        if (ownable != null && ownable) {
            return repository.findAllOwnableNames();
        }
        else return repository.findAllNames();
    }

    public List<String> findAllStarterNames() {
        return repository.findAllStarterNames();
    }

    public Species findByDbid(int dbid) {
        return repository.findByDbid(dbid);
    }

    public List<Species> findByDexno(int dexno) { return repository.findByDexno(dexno); }

    public Species findFirstByDexno(int dexno) { return repository.findFirstByDexno(dexno); }

    public Integer findMaxDexno() { return repository.findMaxDexno(); }

    public Species findByName(String name) {
        Species species = findByNameExact(name);
        if (species == null && name != null) {
            return repository.findFirstByNameStartingWith(name);
        }
        else return species;
    }

    public Species findByNameExact(String name) {
        return repository.findByName(name);
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
        return repository.findByPreEvolution(species);
    }

    public List<Species> findByPreMega(Species species) { return repository.findByPreMega(species); }

    public Species create(SpeciesInputDto input) {
        Species species = new Species(input);

        updateEmbeddedValues(species, input);

        repository.save(species);

        updateAssociatedValues(species, input);

        return species;
    }

    public Species update(SpeciesInputDto input, int dbid) {
        Species species = repository.findByDbid(dbid);
        if (species != null) {
            species.update(input);

            updateEmbeddedValues(species, input);
            repository.save(species);

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
        updateCosmeticForms(species, input);
    }

    private void updateSpeciesAttacks(Species species, SpeciesInputDto input) {
        List<SpeciesAttackInputDto> attacks = input.getAttacks();
        for (SpeciesAttackInputDto attack : attacks) {
            speciesAttackService.update(species, attack);
        }
        species.setAttacks(speciesAttackService.findBySpecies(species));
    }

    private void updateSpeciesAbilities(Species species, SpeciesInputDto input) {
        List<SpeciesAbilityInputDto> abilities = input.getAbilities();
        for (SpeciesAbilityInputDto ability : abilities) {
            speciesAbilityService.update(species, ability);
        }
        species.setAbilities(speciesAbilityService.findBySpecies(species));
    }

    private void updateCosmeticForms(Species species, SpeciesInputDto input) {
        List<CosmeticFormInputDto> forms = input.getCosmeticForms();
        for (CosmeticFormInputDto form : forms) {
            cosmeticFormService.update(form, species.getDbid());
        }
        species.setCosmeticForms(cosmeticFormService.findBySpeciesDbid(species.getDbid()));
    }
}
