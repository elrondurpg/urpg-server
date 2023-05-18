package com.pokemonurpg.configuration.v1.champions;

import com.pokemonurpg.infrastructure.v1.data.jpa.ChampionRepository;
import com.pokemonurpg.entities.v1.Champion;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.configuration.v1.members.MemberRequest;
import com.pokemonurpg.entities.v1.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChampionService implements NamedObjectService<Champion> {

    @Resource
    private ChampionRepository championRepository;

    public List<String> findAllNames() {
        return championRepository.findAllNames();
    }

    public Champion findByDbid(int dbid) {
        return championRepository.findByDbid(dbid);
    }

    public Champion findByName(String name) {
        Champion champion = findByNameExact(name);
        if (champion == null && name != null) {
            return championRepository.findFirstByNameStartingWith(name);
        }
        else return champion;
    }

    @Override
    public Champion findByNameExact(String name) {
        return championRepository.findByName(name);
    }

    public Champion create(String name) {
        Champion champion = championRepository.findByName(name);
        if (champion == null) {
            champion = new Champion(name);
            championRepository.save(champion);
        }
        return champion;
    }

    public Champion create(ChampionRequest input) {
        Champion champion = new Champion(input);
        championRepository.save(champion);
        return champion;
    }

    public Champion update(ChampionRequest input, int dbid) {
        Champion champion = championRepository.findByDbid(dbid);
        if (champion != null) {
            champion.update(input);
            championRepository.save(champion);
        }
        return champion;
    }

    public void rename(MemberRequest input, Member member) {
        Champion champion = findByNameExact(member.getName());
        if (champion != null) {
            champion.setName(input.getName());
            championRepository.save(champion);
        }
    }
}
