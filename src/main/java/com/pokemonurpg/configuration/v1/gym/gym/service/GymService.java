package com.pokemonurpg.configuration.v1.gym.gym.service;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.gym.badge.service.BadgeService;
import com.pokemonurpg.configuration.v1.gym.gym.input.GymInputDto;
import com.pokemonurpg.configuration.v1.gym.gym.model.Gym;
import com.pokemonurpg.configuration.v1.gym.gym.repository.GymRepository;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.repository.GymOwnershipTermRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymService extends NamedConfigurationService<Gym, GymInputDto> {

    private GymRepository repository;
    private BadgeService badgeService;
    private TypeService typeService;
    private GymOwnershipTermRepository gymOwnershipTermRepository;
    private GymPokemonService gymPokemonService;

    @Autowired
    public GymService(
        GymRepository repo,
        BadgeService badgeService,
        TypeService typeService,
        GymOwnershipTermRepository gymOwnershipTermRepository,
        GymPokemonService gymPokemonService) {
        super(repo, Gym.class);
        this.repository = repo;
        this.badgeService = badgeService;
        this.typeService = typeService;
        this.gymOwnershipTermRepository = gymOwnershipTermRepository;
        this.gymPokemonService = gymPokemonService;
    }

    public Gym findByCurrentOwnerRecord(GymOwnershipTerm ownerRecord) {
        return repository.findByCurrentOwnerRecord(ownerRecord);
    }

    public void updateCurrentOwnerRecord(Gym gym, GymOwnershipTerm record) {
        gym.setCurrentOwnerRecord(record);
        repository.save(gym);
    }

    @Override
    protected void updateEmbeddedValues(Gym gym, GymInputDto input) {
        gym.setType(typeService.findByName(input.getType()));
        gym.setBadge(badgeService.findByName(input.getBadge()));
        gymPokemonService.updateAll(input, gym);
        if (input.getCurrentOwnerRecordDbid() != null)
            gym.setCurrentOwnerRecord(gymOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            gym.setCurrentOwnerRecord(null);
            gym.getPokemon().clear();
        }
    }

    @Override
    protected void updateAssociatedValues(Gym model, GymInputDto input) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void deleteAssociatedValues(Gym model) {
        // TODO Auto-generated method stub
        
    }
}
