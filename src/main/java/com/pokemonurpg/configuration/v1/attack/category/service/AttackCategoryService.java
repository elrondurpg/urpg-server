package com.pokemonurpg.configuration.v1.attack.category.service;

import com.pokemonurpg.configuration.v1.attack.category.input.AttackCategoryInputDto;
import com.pokemonurpg.entities.v1.attack.AttackCategory;
import com.pokemonurpg.entities.v1.attack.AttackCategoryRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AttackCategoryService extends SimpleNamedConfigurationService<AttackCategory, AttackCategoryInputDto> {

    @Autowired
    public AttackCategoryService(AttackCategoryRepository repository) {
        super(repository, AttackCategory.class);
    }
}
