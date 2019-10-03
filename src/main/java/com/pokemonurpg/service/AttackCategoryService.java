package com.pokemonurpg.service;

import com.pokemonurpg.object.AttackCategory;
import com.pokemonurpg.repository.AttackCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttackCategoryService {

    private AttackCategoryRepository attackCategoryRepository;

    @Autowired
    public AttackCategoryService(AttackCategoryRepository attackCategoryRepository) {
        this.attackCategoryRepository = attackCategoryRepository;
    }

    public List<Object> findAll() {
        return attackCategoryRepository.findAllNames();
    }

    public AttackCategory findByDbid(int dbid) {
        return attackCategoryRepository.findByDbid(dbid);
    }

    public AttackCategory findByName(String name) {
        return attackCategoryRepository.findByName(name);
    }

    public List<AttackCategory> findByNameStartingWith(String name) {
        return attackCategoryRepository.findByNameStartingWith(name);
    }

    public void save(AttackCategory attackCategory) {
        attackCategoryRepository.save(attackCategory);
    }

    public void delete(AttackCategory attackCategory) {
        attackCategoryRepository.delete(attackCategory);
    }
}
