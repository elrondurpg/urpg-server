package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownChampion;
import com.pokemonurpg.gym.input.KnownChampionInputDto;
import com.pokemonurpg.gym.repository.KnownChampionRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
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

    public KnownChampion findByDbid(int dbid) {
        return knownChampionRepository.findByDbid(dbid);
    }

    public KnownChampion findByName(String name) {
        KnownChampion knownChampion = findByNameExact(name);
        if (knownChampion == null && name != null) {
            return knownChampionRepository.findFirstByNameStartingWith(name);
        }
        else return knownChampion;
    }

    @Override
    public KnownChampion findByNameExact(String name) {
        return knownChampionRepository.findByName(name);
    }

    public KnownChampion create(String name) {
        KnownChampion knownChampion = knownChampionRepository.findByName(name);
        if (knownChampion == null) {
            knownChampion = new KnownChampion(name);
            knownChampionRepository.save(knownChampion);
        }
        return knownChampion;
    }

    public KnownChampion create(KnownChampionInputDto input) {
        KnownChampion knownChampion = new KnownChampion(input);
        knownChampionRepository.save(knownChampion);
        return knownChampion;
    }

    public KnownChampion update(KnownChampionInputDto input, int dbid) {
        KnownChampion knownChampion = knownChampionRepository.findByDbid(dbid);
        if (knownChampion != null) {
            knownChampion.update(input);
            knownChampionRepository.save(knownChampion);
        }
        return knownChampion;
    }

    public void rename(MemberInputDto input, Member member) {
        KnownChampion knownChampion = findByNameExact(member.getName());
        if (knownChampion != null) {
            knownChampion.setName(input.getName());
            knownChampionRepository.save(knownChampion);
        }
    }
}
