package com.pokemonurpg.configuration.v1.gym.elitefour.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.elitefour.input.EliteFourInputDto;
import com.pokemonurpg.configuration.v1.gym.elitefour.model.EliteFour;
import com.pokemonurpg.configuration.v1.gym.elitefour.repository.EliteFourRepository;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.model.EliteFourOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.elitefourownershipterm.repository.EliteFourOwnershipTermRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliteFourService extends NamedConfigurationService<EliteFour, EliteFourInputDto> {

    private EliteFourRepository repository;
    private EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository;
    private EliteFourPokemonService eliteFourPokemonService;

    @Autowired
    public EliteFourService(
            EliteFourRepository repository,
            EliteFourOwnershipTermRepository eliteFourOwnershipTermRepository,
            EliteFourPokemonService eliteFourPokemonService) {
        super(repository, EliteFour.class);
        this.repository = repository;
        this.eliteFourOwnershipTermRepository = eliteFourOwnershipTermRepository;
        this.eliteFourPokemonService = eliteFourPokemonService;
    }

    public EliteFour findByCurrentOwnerRecord(EliteFourOwnershipTerm ownerRecord) {
        return repository.findByCurrentOwnerRecord(ownerRecord);
    }

    public void updateCurrentOwnerRecord(EliteFour gym, EliteFourOwnershipTerm record) {
        gym.setCurrentOwnerRecord(record);
        repository.save(gym);
    }

    @Override
    protected void updateEmbeddedValues(EliteFour gym, EliteFourInputDto input) {
        eliteFourPokemonService.updateAll(input, gym);
        if (input.getCurrentOwnerRecordDbid() != null)
            gym.setCurrentOwnerRecord(eliteFourOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            gym.setCurrentOwnerRecord(null);
            gym.getPokemon().clear();
        }
    }

    @Override
    protected void updateAssociatedValues(EliteFour model, EliteFourInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(EliteFour model) {
        // TODO Auto-generated method stub
        
    }
}
