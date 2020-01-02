package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.object.pokemon.ORASContestMoveType;
import com.pokemonurpg.repository.ORASContestMoveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ORASContestMoveTypeService {

    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;

    @Autowired
    public ORASContestMoveTypeService(ORASContestMoveTypeRepository orasContestMoveTypeRepository) {
        this.orasContestMoveTypeRepository = orasContestMoveTypeRepository;
    }

    public List<Object> findAll() {
        return orasContestMoveTypeRepository.findAllNames();
    }

    public List<ORASContestMoveType> findAllFull() {
        return orasContestMoveTypeRepository.findAll();
    }

    public ORASContestMoveType findByDbid(int dbid) {
        return orasContestMoveTypeRepository.findByDbid(dbid);
    }

    public ORASContestMoveType findByName(String name) {
        return orasContestMoveTypeRepository.findByName(name);
    }

    public List<ORASContestMoveType> findByNameStartingWith(String name) {
        return orasContestMoveTypeRepository.findByNameStartingWith(name);
    }

    public void save(ORASContestMoveType orasContestMoveType) {
        orasContestMoveTypeRepository.save(orasContestMoveType);
    }

    public void delete(ORASContestMoveType orasContestMoveType) {
        orasContestMoveTypeRepository.delete(orasContestMoveType);
    }
}
