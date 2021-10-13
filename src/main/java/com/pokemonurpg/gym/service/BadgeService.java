package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.input.BadgeInputDto;
import com.pokemonurpg.gym.models.Gym;
import com.pokemonurpg.gym.repository.BadgeRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.gym.repository.GymRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BadgeService implements NamedObjectService<Badge> {

    @Resource
    private BadgeRepository badgeRepository;

    @Resource
    private GymService gymService;

    public List<String> findAllNames() {
        return badgeRepository.findAllNames();
    }

    public Badge findByDbid(int dbid) {
        return badgeRepository.findByDbid(dbid);
    }

    public Badge findByName(String name) {
        Badge badge = findByNameExact(name);
        if (badge == null && name != null) {
            return badgeRepository.findFirstByNameStartingWith(name);
        }
        else return badge;
    }

    @Override
    public Badge findByNameExact(String name) {
        return badgeRepository.findByName(name);
    }

    public Badge create(BadgeInputDto input) {
        Badge badge = new Badge(input);
        badgeRepository.save(badge);
        return badge;
    }

    public Badge update(BadgeInputDto input, int dbid) {
        Badge badge = badgeRepository.findByDbid(dbid);
        if (badge != null) {
            badge.update(input);
            badgeRepository.save(badge);
        }
        return badge;
    }

    public void delete(int dbid) {
        badgeRepository.deleteByDbid(dbid);
    }
}
