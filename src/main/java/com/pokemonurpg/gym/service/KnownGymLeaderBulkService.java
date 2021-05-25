package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownGymLeaderBulkInputDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnownGymLeaderBulkService {

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    public List<String> update(KnownGymLeaderBulkInputDto input) {
        input.getLeaders().forEach(knownGymLeaderService::update);
        return knownGymLeaderService.findAllNames();
    }
}
