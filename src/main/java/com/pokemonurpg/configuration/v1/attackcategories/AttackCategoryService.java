package com.pokemonurpg.configuration.v1.attackcategories;

import com.pokemonurpg.entities.v1.AttackCategory;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackCategoryRepository;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttackCategoryService implements NamedObjectService<AttackCategory> {

    @Resource
    private AttackCategoryRepository attackCategoryRepository;

    public List<String> findAllNames() {
        return attackCategoryRepository.findAllNames();
    }

    public AttackCategory findByDbid(int dbid) {
        return attackCategoryRepository.findByDbid(dbid);
    }

    public AttackCategory findByName(String name) {
        AttackCategory attackCategory = attackCategoryRepository.findByName(name);
        if (attackCategory == null && name != null) {
            return attackCategoryRepository.findFirstByNameStartingWith(name);
        }
        else return attackCategory;
    }

    @Override
    public AttackCategory findByNameExact(String name) {
        return attackCategoryRepository.findByName(name);
    }

    public AttackCategory create(AttackCategoryInputDto input) {
        AttackCategory attackCategory = new AttackCategory(input);
        attackCategoryRepository.save(attackCategory);
        return attackCategory;
    }

    public AttackCategory update(AttackCategoryInputDto input, int dbid) {
        AttackCategory attackCategory = attackCategoryRepository.findByDbid(dbid);
        if (attackCategory != null) {
            attackCategory.update(input);
            attackCategoryRepository.save(attackCategory);
        }
        return attackCategory;
    }
}
