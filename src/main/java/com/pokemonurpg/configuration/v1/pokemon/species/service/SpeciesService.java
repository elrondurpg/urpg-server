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
        Species species = new Species();
        updateBase(species, input);
		if (species.getDisplayName() == null) species.setDisplayName(species.getName());
        if (species.isBattleOnly() == null) species.setBattleOnly(false);
        if (species.getLegendaryTier() == null) species.setLegendaryTier(0);
        if (species.getFemaleAllowed() == null) species.setFemaleAllowed(false);
        if (species.getMaleAllowed() == null) species.setMaleAllowed(false);
        return species;
    }

    protected void updateBase(Species species, SpeciesInputDto input) {
        species.setDexno(input.getDexno());
        species.setName(input.getName());
        species.setClassification(input.getClassification());
        species.setHp(input.getHp());
        species.setAttack(input.getAttack());
        species.setDefense(input.getDefense());
        species.setSpecialAttack(input.getSpecialAttack());
        species.setSpecialDefense(input.getSpecialDefense());
        species.setSpeed(input.getSpeed());
        species.setHeight(input.getHeight());
        species.setWeight(input.getWeight());
        species.setMaleAllowed(input.getMaleAllowed());
        species.setFemaleAllowed(input.getFemaleAllowed());
        species.setPokemart(input.getPokemart());
        species.setContestCredits(input.getContestCredits());
        species.setDisplayName(input.getDisplayName());
        species.setFormName(input.getFormName());
        species.setLegendaryTier(input.getLegendaryTier());
        species.setAlteredFormMethod(input.getAlteredFormMethod());
        species.setEvolutionMethod(input.getEvolutionMethod());
        species.setEvolutionExpRequirement(input.getEvolutionExpRequirement());
        species.setMegaStone(input.getMegaStone());
        species.setMegaSuffix(input.getMegaSuffix());
        species.setBattleOnly(input.getBattleOnly());
    }

    protected void updateEmbeddedValues(Species species, SpeciesInputDto input) {
        species.setType1(typeService.findByName(input.getType1()));
        species.setType2(typeService.findByName(input.getType2()));
        species.setStoryRank(storyRankService.findByName(input.getStoryRank()));
        species.setArtRank(artRankService.findByName(input.getArtRank()));
        species.setParkRank(parkRankService.findByName(input.getParkRank()));
        species.setParkLocation(parkLocationService.findByName(input.getParkLocation()));
        species.setPreMega(findByName(input.getPreMega()));
        species.setPreEvolution(findByName(input.getPreEvolution()));
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
