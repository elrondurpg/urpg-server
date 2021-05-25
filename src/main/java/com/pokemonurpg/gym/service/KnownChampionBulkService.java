package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownChampionBulkInputDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownChampionBulkService {

    @Resource
    private KnownChampionService knownChampionService;

    public List<String> update(KnownChampionBulkInputDto input) {
        input.getChampions().forEach(knownChampionService::update);
        return knownChampionService.findAllNames();
    }
}
