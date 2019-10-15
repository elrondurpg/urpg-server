package com.pokemonurpg.service;

import com.pokemonurpg.object.ContestAttribute;
import com.pokemonurpg.repository.ContestAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContestAttributeService {

    private ContestAttributeRepository contestAttributeRepository;

    @Autowired
    public ContestAttributeService(ContestAttributeRepository contestAttributeRepository) {
        this.contestAttributeRepository = contestAttributeRepository;
    }

    public List<Object> findAll() {
        return contestAttributeRepository.findAllNames();
    }

    public List<ContestAttribute> findAllFull() {
        return contestAttributeRepository.findAll();
    }

    public ContestAttribute findByDbid(int dbid) {
        return contestAttributeRepository.findByDbid(dbid);
    }

    public ContestAttribute findByName(String name) {
        return contestAttributeRepository.findByName(name);
    }

    public List<ContestAttribute> findByNameStartingWith(String name) {
        return contestAttributeRepository.findByNameStartingWith(name);
    }

    public void save(ContestAttribute contestAttribute) {
        contestAttributeRepository.save(contestAttribute);
    }

    public void delete(ContestAttribute contestAttribute) {
        contestAttributeRepository.delete(contestAttribute);
    }
}
