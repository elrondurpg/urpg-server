package com.pokemonurpg.configuration.v1.pokemon.species.service;

import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.species.input.CosmeticFormInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAbilityInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.input.SpeciesInputDto;
import com.pokemonurpg.configuration.v1.pokemon.species.model.Species;
import com.pokemonurpg.configuration.v1.pokemon.species.repository.SpeciesRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SpeciesService extends NamedConfigurationService<Species, SpeciesInputDto> {

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

    @Autowired
    public SpeciesService(SpeciesRepository repository) {
        super(repository);
    }

    protected Species createBase(SpeciesInputDto input) {
        return new Species();
    }

    protected void updateBase(Species species, SpeciesInputDto input) {
        set(input.getDexno(), species::setDexno);
        set(input.getName(), species::setName);
        set(input.getClassification(), species::setClassification);
        set(input.getHp(), species::setHp);
        set(input.getAttack(), species::setAttack);
        set(input.getDefense(), species::setDefense);
        set(input.getSpecialAttack(), species::setSpecialAttack);
        set(input.getSpecialDefense(), species::setSpecialDefense);
        set(input.getSpeed(), species::setSpeed);
        set(input.getHeight(), species::setHeight);
        set(input.getWeight(), species::setWeight);
        set(input.getMaleAllowed(), species::setMaleAllowed);
        set(input.getFemaleAllowed(), species::setFemaleAllowed);
        set(input.getPokemart(), species::setPokemart);
        set(input.getContestCredits(), species::setContestCredits);
        set(input.getDisplayName(), species::setDisplayName);
        set(input.getFormName(), species::setFormName);
        set(input.getLegendaryTier(), species::setLegendaryTier);
        set(input.getAlteredFormMethod(), species::setAlteredFormMethod);
        set(input.getEvolutionMethod(), species::setEvolutionMethod);
        set(input.getEvolutionExpRequirement(), species::setEvolutionExpRequirement);
        set(input.getMegaStone(), species::setMegaStone);
        set(input.getMegaSuffix(), species::setMegaSuffix);
        set(input.getBattleOnly(), species::setBattleOnly);
    }

    protected void updateEmbeddedValues(Species species, SpeciesInputDto input) {
        set(typeService.findByName(input.getType1()), species::setType1);
        set(typeService.findByName(input.getType2()), species::setType2);
        set(storyRankService.findByName(input.getStoryRank()), species::setStoryRank);
        set(artRankService.findByName(input.getArtRank()), species::setArtRank);
        set(parkRankService.findByName(input.getParkRank()), species::setParkRank);
        set(parkLocationService.findByName(input.getParkLocation()), species::setParkLocation);
        set(findByName(input.getPreMega()), species::setPreMega);
        set(findByName(input.getPreEvolution()), species::setPreEvolution);
    }

    protected void updateAssociatedValues(Species species, SpeciesInputDto input) {
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

    protected void deleteAssociatedValues(Species species) {
        species.getAttacks().forEach(record -> speciesAttackService.delete(record));
        species.getAbilities().forEach(record -> speciesAbilityService.delete(record));
        species.getCosmeticForms().forEach(record -> cosmeticFormService.delete(record));
    }
}