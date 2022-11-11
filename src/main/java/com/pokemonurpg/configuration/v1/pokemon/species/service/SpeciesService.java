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

import java.util.List;

@Service
public class SpeciesService extends NamedConfigurationService<Species, SpeciesInputDto> {

    private TypeService typeService;
    private StoryRankService storyRankService;
    private ArtRankService artRankService;
    private ParkRankService parkRankService;
    private ParkLocationService parkLocationService;
    private SpeciesAttackService speciesAttackService;
    private SpeciesAbilityService speciesAbilityService;
    private CosmeticFormService cosmeticFormService;

    @Autowired
    public SpeciesService(SpeciesRepository repository, TypeService typeService,
            StoryRankService storyRankService, ArtRankService artRankService, ParkRankService parkRankService,
            ParkLocationService parkLocationService, SpeciesAttackService speciesAttackService,
            SpeciesAbilityService speciesAbilityService, CosmeticFormService cosmeticFormService) {
        super(repository);
        this.typeService = typeService;
        this.storyRankService = storyRankService;
        this.artRankService = artRankService;
        this.parkRankService = parkRankService;
        this.parkLocationService = parkLocationService;
        this.speciesAttackService = speciesAttackService;
        this.speciesAbilityService = speciesAbilityService;
        this.cosmeticFormService = cosmeticFormService;
    }

    protected Species createBase(SpeciesInputDto input) {
        return new Species();
    }

    protected void updateBase(Species species, SpeciesInputDto input) {
        setIfNotNull(input.getDexno(), species::setDexno);
        setIfNotNull(input.getName(), species::setName);
        setIfNotNull(input.getClassification(), species::setClassification);
        setIfNotNull(input.getHp(), species::setHp);
        setIfNotNull(input.getAttack(), species::setAttack);
        setIfNotNull(input.getDefense(), species::setDefense);
        setIfNotNull(input.getSpecialAttack(), species::setSpecialAttack);
        setIfNotNull(input.getSpecialDefense(), species::setSpecialDefense);
        setIfNotNull(input.getSpeed(), species::setSpeed);
        setIfNotNull(input.getHeight(), species::setHeight);
        setIfNotNull(input.getWeight(), species::setWeight);
        setIfNotNull(input.getMaleAllowed(), species::setMaleAllowed);
        setIfNotNull(input.getFemaleAllowed(), species::setFemaleAllowed);
        setIfNotNull(input.getPokemart(), species::setPokemart);
        setIfNotNull(input.getContestCredits(), species::setContestCredits);
        setIfNotNull(input.getDisplayName(), species::setDisplayName);
        setIfNotNull(input.getFormName(), species::setFormName);
        setIfNotNull(input.getLegendaryTier(), species::setLegendaryTier);
        setIfNotNull(input.getAlteredFormMethod(), species::setAlteredFormMethod);
        setIfNotNull(input.getEvolutionMethod(), species::setEvolutionMethod);
        setIfNotNull(input.getEvolutionExpRequirement(), species::setEvolutionExpRequirement);
        setIfNotNull(input.getMegaStone(), species::setMegaStone);
        setIfNotNull(input.getMegaSuffix(), species::setMegaSuffix);
        setIfNotNull(input.getBattleOnly(), species::setBattleOnly);
    }

    protected void updateEmbeddedValues(Species species, SpeciesInputDto input) {
        setIfNotNull(typeService.findByName(input.getType1()), species::setType1);
        setIfNotNull(typeService.findByName(input.getType2()), species::setType2);
        setIfNotNull(storyRankService.findByName(input.getStoryRank()), species::setStoryRank);
        setIfNotNull(artRankService.findByName(input.getArtRank()), species::setArtRank);
        setIfNotNull(parkRankService.findByName(input.getParkRank()), species::setParkRank);
        setIfNotNull(parkLocationService.findByName(input.getParkLocation()), species::setParkLocation);
        setIfNotNull(findByName(input.getPreMega()), species::setPreMega);
        setIfNotNull(findByName(input.getPreEvolution()), species::setPreEvolution);
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
            cosmeticFormService.update(species, form);
        }
        species.setCosmeticForms(cosmeticFormService.findBySpeciesDbid(species.getDbid()));
    }

    protected void deleteAssociatedValues(Species species) {
        species.getAttacks().forEach(record -> speciesAttackService.delete(record));
        species.getAbilities().forEach(record -> speciesAbilityService.delete(record));
        species.getCosmeticForms().forEach(record -> cosmeticFormService.delete(record));
    }
}