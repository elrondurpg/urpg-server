package com.pokemonurpg.service;

import com.pokemonurpg.object.RSEContestMoveType;
import com.pokemonurpg.repository.RSEContestMoveTypeRepository;
import com.pokemonurpg.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RSEContestMoveTypeService {

    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;

    @Autowired
    public RSEContestMoveTypeService(RSEContestMoveTypeRepository rseContestMoveTypeRepository) {
        this.rseContestMoveTypeRepository = rseContestMoveTypeRepository;
    }

    public List<Object> findAll() {
        return rseContestMoveTypeRepository.findAllNames();
    }

    public List<RSEContestMoveType> findAllFull() {
        return rseContestMoveTypeRepository.findAll();
    }

    public RSEContestMoveType findByDbid(int dbid) {
        return rseContestMoveTypeRepository.findByDbid(dbid);
    }

    public RSEContestMoveType findByName(String name) {
        return rseContestMoveTypeRepository.findByName(name);
    }

    public List<RSEContestMoveType> findByNameStartingWith(String name) {
        return rseContestMoveTypeRepository.findByNameStartingWith(name);
    }

    public void save(RSEContestMoveType rseContestMoveType) {
        rseContestMoveTypeRepository.save(rseContestMoveType);
    }

    public void delete(RSEContestMoveType rseContestMoveType) {
        rseContestMoveTypeRepository.delete(rseContestMoveType);
    }
}
