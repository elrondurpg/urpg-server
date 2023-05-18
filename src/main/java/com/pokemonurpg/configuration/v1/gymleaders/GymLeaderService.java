package com.pokemonurpg.configuration.v1.gymleaders;

import com.pokemonurpg.infrastructure.v1.data.jpa.GymLeaderRepository;
import com.pokemonurpg.entities.v1.GymLeader;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.configuration.v1.members.MemberRequest;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GymLeaderService implements NamedObjectService<GymLeader> {

    @Resource
    private GymLeaderRepository gymLeaderRepository;

    public List<String> findAllNames() {
        return gymLeaderRepository.findAllNames();
    }

    public GymLeader findByDbid(int dbid) {
        return gymLeaderRepository.findByDbid(dbid);
    }

    public GymLeader findByName(String name) {
        GymLeader gymLeader = findByNameExact(name);
        if (gymLeader == null && name != null) {
            return gymLeaderRepository.findFirstByNameStartingWith(name);
        }
        else return gymLeader;
    }

    @Override
    public GymLeader findByNameExact(String name) {
        return gymLeaderRepository.findByName(name);
    }

    public GymLeader create(GymLeaderRequest input) {
        GymLeader gymLeader = new GymLeader(input);
        gymLeaderRepository.save(gymLeader);
        return gymLeader;
    }

    public GymLeader create(String name) {
        GymLeader gymLeader = gymLeaderRepository.findByName(name);
        if (gymLeader == null) {
            gymLeader = new GymLeader(name);
            gymLeaderRepository.save(gymLeader);
        }
        return gymLeader;
    }

    public GymLeader update(GymLeaderRequest input, int dbid) {
        GymLeader gymLeader = gymLeaderRepository.findByDbid(dbid);
        if (gymLeader != null) {
            gymLeader.update(input);
            gymLeaderRepository.save(gymLeader);
        }
        return gymLeader;
    }

    public void rename(MemberRequest input, Member member) {
        GymLeader gymLeader = findByNameExact(member.getName());
        if (gymLeader != null) {
            gymLeader.setName(input.getName());
            gymLeaderRepository.save(gymLeader);
        }
    }
}
