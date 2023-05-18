package com.pokemonurpg.pokedex.v1;

import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PokedexService {

    @Resource
    private PokemonService pokemonService;

    @Resource
    private SpeciesPageTabService speciesPageTabService;

    @Resource
    private AlteredFormService alteredFormService;

    @Resource
    private EvolutionFamilyService evolutionFamilyService;

    @Resource
    private MegaEvolutionService megaEvolutionService;

    @Resource
    private TypeMatchupService typeMatchupService;

    public PokemonResponse findByName(String name) {
        Pokemon pokemon = pokemonService.findByName(name);
        if (pokemon != null) {
            pokemon = switchToPreMegaIfNeeded(pokemon);

            PokemonResponse pokedexEntry = new PokemonResponse(pokemon);
            pokedexEntry.setPrevDex(speciesPageTabService.findPrevDexBySpecies(pokemon));
            pokedexEntry.setNextDex(speciesPageTabService.findNextDexBySpecies(pokemon));
            pokedexEntry.setAlteredForms(alteredFormService.findBySpecies(pokemon));
            pokedexEntry.buildAttacksThatDifferByForm();
            pokedexEntry.setEvolutionFamily(evolutionFamilyService.findBySpecies(pokemon));
            pokedexEntry.setMegaEvolutions(megaEvolutionService.findBySpecies(pokemon));
            pokedexEntry.setTypeMatchups(typeMatchupService.findBySpecies(pokemon));
            return pokedexEntry;
        }
        return null;
    }

    public Pokemon switchToPreMegaIfNeeded(Pokemon pokemon) {
        Pokemon preMega = pokemon.getPreMega();
        if (preMega != null) {
            // TODO set a "jump to" parameter so that the pokedex page jumps to this mega
            return preMega;
        }
        else return pokemon;
    }
}