package com.pokemonurpg.service;

import com.pokemonurpg.dto.species.input.SpeciesAttackInputDto;
import com.pokemonurpg.dto.species.response.SpeciesAttackDto;
import com.pokemonurpg.object.Attack;
import com.pokemonurpg.object.SpeciesAttack;
import com.pokemonurpg.repository.AttackRepository;
import com.pokemonurpg.repository.SpeciesAttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpeciesAttackService {

    private SpeciesAttackRepository speciesAttackRepository;

    private AttackRepository attackRepository;

    @Autowired
    public SpeciesAttackService(SpeciesAttackRepository speciesAttackRepository, AttackRepository attackRepository) {
        this.speciesAttackRepository = speciesAttackRepository;
        this.attackRepository = attackRepository;
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

    public void create(int speciesDbid, SpeciesAttackInputDto input) {
        Attack attack = attackRepository.findByName(input.getName());
        SpeciesAttack speciesAttack = new SpeciesAttack(speciesDbid, attack.getDbid(), input.getMethod(), input.getGeneration());
        speciesAttackRepository.save(speciesAttack);
    }

    public void createAll(int speciesDbid, List<SpeciesAttackInputDto> input) {
        for (SpeciesAttackInputDto record : input) {
            create(speciesDbid, record);
        }
    }

    public void update(SpeciesAttack existingRecord, SpeciesAttackInputDto input) {
        if (input.isDeleted()) {
            speciesAttackRepository.delete(existingRecord);
        }
        else {
            if (input.getMethod() != null) {
                existingRecord.setMethod(input.getMethod());
            }
            if (input.getGeneration() != null) {
                existingRecord.setGeneration(input.getGeneration());
            }
            speciesAttackRepository.save(existingRecord);
        }
    }

    public void updateAll(int speciesDbid, List<SpeciesAttackInputDto> input) {
        if (input != null) {
            for (SpeciesAttackInputDto record : input) {
                Attack attack = attackRepository.findByName(record.getName());

                SpeciesAttack existingRecord = speciesAttackRepository.findByIdSpeciesDbidAndIdAttackDbid(speciesDbid, attack.getDbid());
                if (existingRecord != null) {
                    update(existingRecord, record);
                } else if (!record.isDeleted()) {
                    create(speciesDbid, record);
                }
            }
        }
    }
}
