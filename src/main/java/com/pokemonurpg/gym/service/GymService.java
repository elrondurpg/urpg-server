package com.pokemonurpg.gym.service;

import com.pokemonurpg.configuration.v1.gym.badge.service.BadgeService;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.input.GymInputDto;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.model.GymOwnershipTerm;
import com.pokemonurpg.configuration.v1.gym.gymownershipterm.repository.GymOwnershipTermRepository;
import com.pokemonurpg.gym.repository.GymRepository;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GymService implements IndexedObjectService<Gym>, NamedObjectService<Gym> {

    @Resource
    private GymRepository gymRepository;

    @Resource
    private BadgeService badgeService;

    @Resource
    private TypeService typeService;

    @Resource
    private GymOwnershipTermRepository gymOwnershipTermRepository;

    @Resource
    private GymPokemonService gymPokemonService;

    public List<String> findAllNames() {
        return gymRepository.findAllNames();
    }

    public Gym findByDbid(Integer dbid) {
        return gymRepository.findByDbid(dbid);
    }

    public Gym findByName(String name) {
        Gym gym = findByNameExact(name);
        if (gym == null && name != null) {
            return gymRepository.findFirstByNameStartingWith(name);
        }
        else return gym;
    }

    @Override
    public Gym findByNameExact(String name) {
        return gymRepository.findByName(name);
    }

    public Gym findByCurrentOwnerRecord(GymOwnershipTerm ownerRecord) {
        return gymRepository.findByCurrentOwnerRecord(ownerRecord);
    }


    public Gym create(GymInputDto input) {
        Gym gym = new Gym(input);
        updateEmbeddedValues(input, gym);
        gymRepository.save(gym);
        return gym;
    }

    public Gym update(GymInputDto input, int dbid) {
        Gym gym = gymRepository.findByDbid(dbid);
        if (gym != null) {
            gym.update(input);
            updateEmbeddedValues(input, gym);
            gymRepository.save(gym);
        }
        return gym;
    }

    public void updateCurrentOwnerRecord(Gym gym, GymOwnershipTerm record) {
        gym.setCurrentOwnerRecord(record);
        gymRepository.save(gym);
    }

    void updateEmbeddedValues(GymInputDto input, Gym gym) {
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

    public void delete(int dbid) {
        gymRepository.deleteByDbid(dbid);
    }
}
