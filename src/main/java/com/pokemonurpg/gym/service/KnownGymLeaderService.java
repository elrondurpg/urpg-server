package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.models.KnownGymLeader;
import com.pokemonurpg.gym.input.KnownGymLeaderInputDto;
import com.pokemonurpg.gym.repository.KnownGymLeaderRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Member;
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

    public KnownGymLeader findByDbid(int dbid) {
        return knownGymLeaderRepository.findByDbid(dbid);
    }

    public KnownGymLeader findByName(String name) {
        KnownGymLeader knownGymLeader = findByNameExact(name);
        if (knownGymLeader == null && name != null) {
            return knownGymLeaderRepository.findFirstByNameStartingWith(name);
        }
        else return knownGymLeader;
    }

    @Override
    public KnownGymLeader findByNameExact(String name) {
        return knownGymLeaderRepository.findByName(name);
    }

    public KnownGymLeader create(KnownGymLeaderInputDto input) {
        KnownGymLeader knownGymLeader = new KnownGymLeader(input);
        knownGymLeaderRepository.save(knownGymLeader);
        return knownGymLeader;
    }

    public KnownGymLeader create(String name) {
        KnownGymLeader knownGymLeader = knownGymLeaderRepository.findByName(name);
        if (knownGymLeader == null) {
            knownGymLeader = new KnownGymLeader(name);
            knownGymLeaderRepository.save(knownGymLeader);
        }
        return knownGymLeader;
    }

    public KnownGymLeader update(KnownGymLeaderInputDto input, int dbid) {
        KnownGymLeader knownGymLeader = knownGymLeaderRepository.findByDbid(dbid);
        if (knownGymLeader != null) {
            knownGymLeader.update(input);
            knownGymLeaderRepository.save(knownGymLeader);
        }
        return knownGymLeader;
    }

    public void rename(MemberInputDto input, Member member) {
        KnownGymLeader knownGymLeader = findByNameExact(member.getName());
        if (knownGymLeader != null) {
            knownGymLeader.setName(input.getName());
            knownGymLeaderRepository.save(knownGymLeader);
        }
    }
}
