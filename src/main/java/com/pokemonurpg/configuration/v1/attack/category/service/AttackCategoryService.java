package com.pokemonurpg.configuration.v1.attack.category.service;

import com.pokemonurpg.configuration.v1.attack.category.input.AttackCategoryInputDto;
import com.pokemonurpg.configuration.v1.attack.category.model.AttackCategory;
import com.pokemonurpg.configuration.v1.lib.repository.NamedConfigurationRepository;
import com.pokemonurpg.configuration.v1.lib.service.SimpleNamedConfigurationService;

import org.springframework.stereotype.Service;
@Service
public class AttackCategoryService extends SimpleNamedConfigurationService<AttackCategory, AttackCategoryInputDto> {

    public AttackCategoryService(NamedConfigurationRepository<AttackCategory> repository) {
        super(repository, AttackCategory.class);
    }
}
