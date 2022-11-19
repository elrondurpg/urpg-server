package com.pokemonurpg.configuration.v1.attack.category.controller;

import com.pokemonurpg.configuration.v1.attack.category.AttackCategoryViews;
import com.pokemonurpg.configuration.v1.attack.category.service.AttackCategoryService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AttackCategoryControllerTest {

    @InjectMocks
    private AttackCategoryController controller;

    @Mock
    private AttackCategoryService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(AttackCategoryViews.Id.class, controller.getIdViewClass());
        assertEquals(AttackCategoryViews.Id.class, controller.getBriefViewClass());
        assertEquals(AttackCategoryViews.Id.class, controller.getFullViewClass());
    }
}