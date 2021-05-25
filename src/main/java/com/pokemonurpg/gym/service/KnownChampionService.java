package com.pokemonurpg.gym.service;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.repository.KnownChampionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownChampionService implements NamedObjectService<KnownChampion> {

    @Resource
    private KnownChampionRepository knownChampionRepository;

    public List<String> findAllNames() {
        return knownChampionRepository.findAllNames();
    }

    public KnownChampion findByName(String name) {
        KnownChampion champion = knownChampionRepository.findByName(name);
        if (champion == null && name != null) {
            return knownChampionRepository.findFirstByNameStartingWith(name);
        }
        else return champion;
    }

    public void update(KnownChampionInputDto input) {
        KnownChampion champion = knownChampionRepository.findByName(input.getName());
        if (champion != null && input.getDelete()) {
            knownChampionRepository.delete(champion);
        }
        else if (champion == null) {
            champion = new KnownChampion(input.getName());
            knownChampionRepository.save(champion);
        }
    }
}
