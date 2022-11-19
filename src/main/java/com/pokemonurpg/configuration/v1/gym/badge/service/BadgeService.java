package com.pokemonurpg.configuration.v1.gym.badge.service;

import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;
import com.pokemonurpg.configuration.v1.gym.badge.input.BadgeInputDto;
import com.pokemonurpg.configuration.v1.gym.badge.model.Badge;
import com.pokemonurpg.configuration.v1.gym.badge.repository.BadgeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService extends SimpleNamedConfigurationService<Badge, BadgeInputDto> {

    @Autowired
    public BadgeService(BadgeRepository repo) {
        super(repo, Badge.class);
    }
}
