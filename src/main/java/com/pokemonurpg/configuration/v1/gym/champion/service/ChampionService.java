package com.pokemonurpg.configuration.v1.gym.champion.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.champion.input.ChampionInputDto;
import com.pokemonurpg.configuration.v1.gym.champion.model.Champion;
import com.pokemonurpg.configuration.v1.gym.champion.repository.ChampionRepository;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.model.ChampionOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.championownershipterm.repository.ChampionOwnershipTermRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionService extends NamedConfigurationService<Champion, ChampionInputDto> {

    private ChampionRepository repository;
    private ChampionOwnershipTermRepository championOwnershipTermRepository;
    private ChampionPokemonService championPokemonService;

    @Autowired
    public ChampionService(
            ChampionRepository repository,
            ChampionOwnershipTermRepository championOwnershipTermRepository,
            ChampionPokemonService championPokemonService) {
        super(repository, Champion.class);
        this.repository = repository;
        this.championOwnershipTermRepository = championOwnershipTermRepository;
        this.championPokemonService = championPokemonService;
    }

    public Champion findByCurrentOwnerRecord(ChampionOwnershipTerm ownerRecord) {
        return repository.findByCurrentOwnerRecord(ownerRecord);
    }

    public void updateCurrentOwnerRecord(Champion gym, ChampionOwnershipTerm record) {
        gym.setCurrentOwnerRecord(record);
        repository.save(gym);
    }

    @Override
    protected void updateEmbeddedValues(Champion gym, ChampionInputDto input) {
        championPokemonService.updateAll(input, gym);
        if (input.getCurrentOwnerRecordDbid() != null)
            gym.setCurrentOwnerRecord(championOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            gym.setCurrentOwnerRecord(null);
            gym.getPokemon().clear();
        }
    }

    @Override
    protected void updateAssociatedValues(Champion model, ChampionInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(Champion model) {
        // TODO Auto-generated method stub
        
    }
}
