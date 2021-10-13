package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;
import com.pokemonurpg.gym.repository.KnownGymLeaderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownGymLeaderService implements NamedObjectService<KnownGymLeader> {

    @Resource
    private KnownGymLeaderRepository knownGymLeaderRepository;

    public List<String> findAllNames() {
        return knownGymLeaderRepository.findAllNames();
    }

    public KnownGymLeader findByName(String name) {
        KnownGymLeader leader = findByNameExact(name);
        if (leader == null && name != null) {
            return knownGymLeaderRepository.findFirstByNameStartingWith(name);
        }
        else return leader;
    }

    public KnownGymLeader findByNameExact(String name) {
        return knownGymLeaderRepository.findByName(name);
    }

    public void create(String name) {
        KnownGymLeader leader = findByNameExact(name);
        if (leader == null) {
            leader = new KnownGymLeader();
            leader.setName(name);
            knownGymLeaderRepository.save(leader);
        }
    }

    public void update(String newName, String oldName) {
        if (newName != null && oldName != null) {
            KnownGymLeader leader = knownGymLeaderRepository.findByName(oldName);
            if (leader != null) {
                leader.setName(newName);
                knownGymLeaderRepository.save(leader);
            }
        }
    }

    public void update(KnownGymLeaderInputDto input) {
        KnownGymLeader leader = knownGymLeaderRepository.findByName(input.getName());
        if (leader != null && input.getDelete()) {
            knownGymLeaderRepository.delete(leader);
        }
        else if (leader == null) {
            leader = new KnownGymLeader(input.getName());
            knownGymLeaderRepository.save(leader);
        }
    }
}
