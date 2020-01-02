package com.pokemonurpg.service.pokemon;

import com.pokemonurpg.object.pokemon.AttackTargetType;
import com.pokemonurpg.repository.AttackTargetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttackTargetTypeService {

    private AttackTargetTypeRepository attackTargetTypeRepository;

    @Autowired
    public AttackTargetTypeService(AttackTargetTypeRepository attackTargetTypeRepository) {
        this.attackTargetTypeRepository = attackTargetTypeRepository;
    }

    public List<Object> findAll() {
        return attackTargetTypeRepository.findAllNames();
    }

    public AttackTargetType findByDbid(int dbid) {
        return attackTargetTypeRepository.findByDbid(dbid);
    }

    public AttackTargetType findByName(String name) {
        return attackTargetTypeRepository.findByName(name);
    }

    public List<AttackTargetType> findByNameStartingWith(String name) {
        return attackTargetTypeRepository.findByNameStartingWith(name);
    }

    public void save(AttackTargetType attackTargetType) {
        attackTargetTypeRepository.save(attackTargetType);
    }

    public void delete(AttackTargetType attackTargetType) {
        attackTargetTypeRepository.delete(attackTargetType);
    }
}
