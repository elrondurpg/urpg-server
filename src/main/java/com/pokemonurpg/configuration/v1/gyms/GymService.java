package com.pokemonurpg.configuration.v1.gyms;

import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.infrastructure.v1.data.jpa.*;
import com.pokemonurpg.lib.v1.services.IndexedObjectService;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.Gym;
import com.pokemonurpg.entities.v1.GymOwnershipTerm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GymService implements IndexedObjectService<Gym>, NamedObjectService<Gym> {

    private final GymRepository gymRepository;
    private final BadgeRepository badgeRepository;
    private final TypeRepository typeRepository;
    private final GymOwnershipTermRepository gymOwnershipTermRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;

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
        return gym;
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

    public Gym update(int dbid, GymInputDto input) {
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
        gym.setType(typeRepository.findByName(input.getType()));
        gym.setBadge(badgeRepository.findByName(input.getBadge()));

        updatePokemon(input, gym);

        if (input.getCurrentOwnerRecordDbid() != null) {
            gym.setCurrentOwnerRecord(gymOwnershipTermRepository.findByDbid(input.getCurrentOwnerRecordDbid()));
        } else if (Boolean.TRUE.equals(input.getRemoveOwner())) {
            gym.setCurrentOwnerRecord(null);
            gym.getPokemon().clear();
        }
    }

    private void updatePokemon(GymInputDto input, Gym gym) {
        Set<OwnedPokemon> pokemons = gym.getPokemon();

        for (GymPokemonInputDto pokemon : input.getPokemon()) {
            Integer dbid = pokemon.getDbid();
            OwnedPokemon ownedPokemon = ownedPokemonRepository.findByDbid(dbid);
            if (pokemon.getDelete()) {
                pokemons.remove(ownedPokemon);
            } else {
                pokemons.add(ownedPokemon);
            }
        }
    }

    public void delete(int dbid) {
        gymRepository.deleteByDbid(dbid);
    }
}
