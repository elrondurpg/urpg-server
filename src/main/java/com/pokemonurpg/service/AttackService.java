package com.pokemonurpg.service;

import com.pokemonurpg.object.Attack;
import com.pokemonurpg.repository.AttackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttackService {

    private AttackRepository attackRepository;

    @Autowired
    public AttackService(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    public List<Attack> findAll() {
        return attackRepository.findAll();
    }

    public Optional<Attack> findByDbid(Integer dbid) {
        Optional<Attack> species = attackRepository.findByDbid(dbid);
        return species;
    }

    public Optional<Attack> findByName(String name) {
        return attackRepository.findByName(name);
    }

    public List<Attack> findByNameStartingWith(String name) {
        return attackRepository.findByNameStartingWith(name);
    }

    public void save(Attack attack) {
        attackRepository.save(attack);
    }

    public void delete(Attack attack) {
        attackRepository.delete(attack);
    }
    
}
