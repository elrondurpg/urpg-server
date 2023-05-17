package com.pokemonurpg.configuration.v1.gyms;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.infrastructure.data.GymOwnershipTermRepository;
import com.pokemonurpg.infrastructure.data.GymRepository;
import com.pokemonurpg.lib.service.IndexedObjectService;
import com.pokemonurpg.lib.service.NamedObjectService;
import com.pokemonurpg.entities.Gym;
import com.pokemonurpg.entities.GymOwnershipTerm;
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
