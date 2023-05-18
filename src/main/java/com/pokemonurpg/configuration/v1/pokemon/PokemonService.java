package com.pokemonurpg.configuration.v1.pokemon;

import com.pokemonurpg.infrastructure.v1.data.jpa.SpeciesRepository;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PokemonService implements NamedObjectService<Pokemon> {

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
    private PokemonAttackService pokemonAttackService;

    @Resource
    private PokemonAbilityService pokemonAbilityService;

    @Resource
    private CosmeticFormService cosmeticFormService;

    public List<String> findAllNames(Boolean ownable) {
        if (ownable != null && ownable) {
            return speciesRepository.findAllOwnableNames();
        }
        else return speciesRepository.findAllNames();
    }

    public List<String> findAllStarterNames() {
        return speciesRepository.findAllStarterNames();
    }

    public Pokemon findByDbid(int dbid) {
        return speciesRepository.findByDbid(dbid);
    }

    public List<Pokemon> findByDexno(int dexno) { return speciesRepository.findByDexno(dexno); }

    public Pokemon findFirstByDexno(int dexno) { return speciesRepository.findFirstByDexno(dexno); }

    public Integer findMaxDexno() { return speciesRepository.findMaxDexno(); }

    public Pokemon findByName(String name) {
        Pokemon pokemon = findByNameExact(name);
        if (pokemon == null && name != null) {
            return speciesRepository.findFirstByNameStartingWith(name);
        }
        else return pokemon;
    }

    public Pokemon findByNameExact(String name) {
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

    public List<Pokemon> findByPreEvolution(Pokemon pokemon) {
        return speciesRepository.findByPreEvolution(pokemon);
    }

    public List<Pokemon> findByPreMega(Pokemon pokemon) { return speciesRepository.findByPreMega(pokemon); }

    public Pokemon create(PokemonRequest input) {
        Pokemon pokemon = new Pokemon(input);

        updateEmbeddedValues(pokemon, input);

        speciesRepository.save(pokemon);

        updateAssociatedValues(pokemon, input);

        return pokemon;
    }

    public Pokemon update(PokemonRequest input, int dbid) {
        Pokemon pokemon = speciesRepository.findByDbid(dbid);
        if (pokemon != null) {
            pokemon.update(input);

            updateEmbeddedValues(pokemon, input);
            speciesRepository.save(pokemon);

            updateAssociatedValues(pokemon, input);
        }
        return pokemon;
    }

    void updateEmbeddedValues(Pokemon pokemon, PokemonRequest input) {
        pokemon.setType1(typeService.findByName(input.getType1()));
        pokemon.setType2(typeService.findByName(input.getType2()));
        pokemon.setStoryRank(storyRankService.findByName(input.getStoryRank()));
        pokemon.setArtRank(artRankService.findByName(input.getArtRank()));
        pokemon.setParkRank(parkRankService.findByName(input.getParkRank()));
        pokemon.setParkLocation(parkLocationService.findByName(input.getParkLocation()));
        pokemon.setPreMega(findByName(input.getPreMega()));
        pokemon.setPreEvolution(findByName(input.getPreEvolution()));
    }

    void updateAssociatedValues(Pokemon pokemon, PokemonRequest input) {
        updateSpeciesAttacks(pokemon, input);
        updateSpeciesAbilities(pokemon, input);
        updateCosmeticForms(pokemon, input);
    }

    private void updateSpeciesAttacks(Pokemon pokemon, PokemonRequest input) {
        List<PokemonAttackRequest> attacks = input.getAttacks();
        for (PokemonAttackRequest attack : attacks) {
            pokemonAttackService.update(pokemon, attack);
        }
        pokemon.setAttacks(pokemonAttackService.findBySpecies(pokemon));
    }

    private void updateSpeciesAbilities(Pokemon pokemon, PokemonRequest input) {
        List<PokemonAbilityRequest> abilities = input.getAbilities();
        for (PokemonAbilityRequest ability : abilities) {
            pokemonAbilityService.update(pokemon, ability);
        }
        pokemon.setAbilities(pokemonAbilityService.findBySpecies(pokemon));
    }

    private void updateCosmeticForms(Pokemon pokemon, PokemonRequest input) {
        List<CosmeticFormRequest> forms = input.getCosmeticForms();
        for (CosmeticFormRequest form : forms) {
            cosmeticFormService.update(form, pokemon.getDbid());
        }
        pokemon.setCosmeticForms(cosmeticFormService.findBySpeciesDbid(pokemon.getDbid()));
    }
}
