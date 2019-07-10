package com.pokemonurpg.service;

import com.pokemonurpg.dto.SpeciesAttackDto;
import com.pokemonurpg.object.Species;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.object.SpeciesAttackKey;
import com.pokemonurpg.repository.SpeciesAttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesAttackService {

    private SpeciesAttackRepository speciesAttackRepository;

    @Autowired
    public SpeciesAttackService(SpeciesAttackRepository speciesAttackRepository) {
        this.speciesAttackRepository = speciesAttackRepository;
    }

    public List<SpeciesAttack> findAll() {
        return speciesAttackRepository.findAll();
    }

    public List<SpeciesAttackDto> findBySpeciesDbid(int dbid) {
        List<SpeciesAttackDto> speciesAttackDtos = new ArrayList<>();
        List<SpeciesAttack> speciesAttacks = speciesAttackRepository.findByIdSpeciesDbid(dbid);
        for (SpeciesAttack speciesAttack : speciesAttacks) {
            SpeciesAttackDto speciesAttackDto = new SpeciesAttackDto(speciesAttack);
            speciesAttackDtos.add(speciesAttackDto);
        }
        return speciesAttackDtos;
    }

    /*public Optional<SpeciesAttack> findBySpeciesAttackKey(SpeciesAttackKey key) {
        return speciesAttackRepository.findById(key);
    }

    public void save(SpeciesAttack sa) {
        if (sa != null) {
            if (sa.internalGetId() != null && sa.internalGetId().getAttackDbid() != null && sa.internalGetId().getSpeciesDbid() != null) {
                speciesAttackRepository.save(sa);
            }
        }
    }

    public void saveSpeciesAttacksFromSpecies(Species species, Species prototype) {
        HashMap<Integer, SpeciesAttack> newAttacks = getAttacksMappedByDbid(species);
        HashMap<Integer, SpeciesAttack> currentAttacks = getAttacksMappedByDbid(prototype);

        for (Integer dbid : newAttacks.keySet()) {
            SpeciesAttack newRecord = newAttacks.get(dbid);
            if (currentAttacks.containsKey(dbid)) {
                SpeciesAttack currentRecord = currentAttacks.get(dbid);
                if(!newRecord.equals(currentRecord)) {
                    newRecord.cloneValuesFrom(currentRecord);
                    save(newRecord);
                }
            }
            else {
                newRecord.setId(new SpeciesAttackKey(newRecord.getSpecies().getDbid(), newRecord.getAttack().getDbid()));
                save(newRecord);
            }
        }

        for (Integer dbid : currentAttacks.keySet()) {
            SpeciesAttack record = currentAttacks.get(dbid);
            if (!newAttacks.containsKey(dbid)) {
                delete(record);
            }
        }
    }

    public HashMap<Integer, SpeciesAttack> getAttacksMappedByDbid(Species species) {
        List<SpeciesAttack> newAttacks = species.getAttacks();
        HashMap<Integer, SpeciesAttack> newAttackMap = new HashMap<>();
        for (SpeciesAttack newAttack : newAttacks) {
            Integer dbid = newAttack.getAttack().getDbid();
            newAttackMap.put(dbid, newAttack);
        }
        return newAttackMap;
    }

    public void delete(SpeciesAttack sa) {
        if (sa != null) {
            if (sa.secretGetSpecies() != null && sa.secretGetSpecies().getDbid() != null && sa.getAttack() != null && sa.getAttack().getDbid() != null) {
                speciesAttackRepository.delete(sa.secretGetSpecies().getDbid(), sa.getAttack().getDbid());
            }
        }
    }*/
}
