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
        KnownGymLeader leader = knownGymLeaderRepository.findByName(name);
        if (leader == null && name != null) {
            return knownGymLeaderRepository.findFirstByNameStartingWith(name);
        }
        else return leader;
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
