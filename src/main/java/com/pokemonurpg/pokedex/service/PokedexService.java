package com.pokemonurpg.pokedex.service;

import com.pokemonurpg.pokedex.output.PokedexEntryDto;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PokedexService {

    @Resource
    private SpeciesService speciesService;

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

    public PokedexEntryDto findByName(String name) {
        Species species = speciesService.findByName(name);
        if (species != null) {
            species = switchToPreMegaIfNeeded(species);

            PokedexEntryDto pokedexEntry = new PokedexEntryDto(species);
            pokedexEntry.setPrevDex(speciesPageTabService.findPrevDexBySpecies(species));
            pokedexEntry.setNextDex(speciesPageTabService.findNextDexBySpecies(species));
            pokedexEntry.setAlteredForms(alteredFormService.findBySpecies(species));
            pokedexEntry.buildAttacksThatDifferByForm();
            pokedexEntry.setEvolutionFamily(evolutionFamilyService.findBySpecies(species));
            pokedexEntry.setMegaEvolutions(megaEvolutionService.findBySpecies(species));
            pokedexEntry.setTypeMatchups(typeMatchupService.findBySpecies(species));
            return pokedexEntry;
        }
        return null;
    }

    public Species switchToPreMegaIfNeeded(Species species) {
        Species preMega = species.getPreMega();
        if (preMega != null) {
            // TODO set a "jump to" parameter so that the pokedex page jumps to this mega
            return preMega;
        }
        else return species;
    }
}