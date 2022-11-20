package com.pokemonurpg.configuration.v1.member.member.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.member.member.MemberViews;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController controller;

    @Mock
    private MemberService service;

    @Test
    public void test_constructor() {
        assertEquals(service, controller.getService());
        assertEquals(MemberViews.Id.class, controller.getIdViewClass());
        assertEquals(MemberViews.Id.class, controller.getBriefViewClass());
        assertEquals(MemberViews.Id.class, controller.getFullViewClass());
    }

}