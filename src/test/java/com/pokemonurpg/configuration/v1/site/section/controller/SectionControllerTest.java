package com.pokemonurpg.configuration.v1.site.section.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.site.section.SectionViews;
import com.pokemonurpg.configuration.v1.site.section.service.SectionService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SectionControllerTest {

    @InjectMocks
    private SectionController controller;

    @Mock
    private SectionService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(SectionViews.Id.class, controller.getIdViewClass());
        assertEquals(SectionViews.Brief.class, controller.getBriefViewClass());
        assertEquals(SectionViews.Brief.class, controller.getFullViewClass());
    }

}