package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.KnownEliteFourMemberViews;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class KnownEliteFourMemberControllerTest {

    @InjectMocks
    private KnownEliteFourMemberController controller;

    @Mock
    private KnownEliteFourMemberService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(KnownEliteFourMemberViews.Id.class, controller.getIdViewClass());
        assertEquals(KnownEliteFourMemberViews.Id.class, controller.getBriefViewClass());
        assertEquals(KnownEliteFourMemberViews.Id.class, controller.getFullViewClass());
    }

}