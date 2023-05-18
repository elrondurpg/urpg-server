package com.pokemonurpg.configuration.v1.contesteffects;

import com.pokemonurpg.infrastructure.v1.data.jpa.ORASContestEffectRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.ORASContestEffect;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ContestEffectService implements NamedObjectService<ORASContestEffect> {

    @Resource
    private ORASContestEffectRepository orasContestEffectRepository;

    public List<String> findAllNames() {
        return orasContestEffectRepository.findAllNames();
    }

    public ORASContestEffect findByDbid(int dbid) {
        return orasContestEffectRepository.findByDbid(dbid);
    }

    public ORASContestEffect findByName(String name) {
        ORASContestEffect orasContestMoveType = findByNameExact(name);
        if (orasContestMoveType == null && name != null) {
            return orasContestEffectRepository.findFirstByNameStartingWith(name);
        }
        else return orasContestMoveType;
    }

    @Override
    public ORASContestEffect findByNameExact(String name) {
        return orasContestEffectRepository.findByName(name);
    }

    public ORASContestEffect create(ContestEffectRequest input) {
        ORASContestEffect orasContestMoveType = new ORASContestEffect(input);
        orasContestEffectRepository.save(orasContestMoveType);
        return orasContestMoveType;
    }

    public ORASContestEffect update(ContestEffectRequest input, int dbid) {
        ORASContestEffect orasContestMoveType = orasContestEffectRepository.findByDbid(dbid);
        if (orasContestMoveType != null) {
            orasContestMoveType.update(input);
            orasContestEffectRepository.save(orasContestMoveType);
        }
        return orasContestMoveType;
    }
}
