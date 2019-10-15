package com.pokemonurpg.service;

import com.pokemonurpg.object.DPPContestMoveType;
import com.pokemonurpg.repository.DPPContestMoveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DPPContestMoveTypeService {

    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    @Autowired
    public DPPContestMoveTypeService(DPPContestMoveTypeRepository dppContestMoveTypeRepository) {
        this.dppContestMoveTypeRepository = dppContestMoveTypeRepository;
    }

    public List<Object> findAll() {
        return dppContestMoveTypeRepository.findAllNames();
    }

    public List<DPPContestMoveType> findAllFull() {
        return dppContestMoveTypeRepository.findAll();
    }

    public DPPContestMoveType findByDbid(int dbid) {
        return dppContestMoveTypeRepository.findByDbid(dbid);
    }

    public DPPContestMoveType findByName(String name) {
        return dppContestMoveTypeRepository.findByName(name);
    }

    public List<DPPContestMoveType> findByNameStartingWith(String name) {
        return dppContestMoveTypeRepository.findByNameStartingWith(name);
    }

    public void save(DPPContestMoveType dppContestMoveType) {
        dppContestMoveTypeRepository.save(dppContestMoveType);
    }

    public void delete(DPPContestMoveType dppContestMoveType) {
        dppContestMoveTypeRepository.delete(dppContestMoveType);
    }
}
