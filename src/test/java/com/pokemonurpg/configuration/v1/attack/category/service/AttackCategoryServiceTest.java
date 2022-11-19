package com.pokemonurpg.configuration.v1.attack.category.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.attack.category.model.AttackCategory;
import com.pokemonurpg.configuration.v1.attack.category.repository.AttackCategoryRepository;

@ExtendWith(MockitoExtension.class)
public class AttackCategoryServiceTest {

    @InjectMocks
    private AttackCategoryService service;

    @Mock
    private AttackCategoryRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(AttackCategory.class, service.getModelClass());
    }
}