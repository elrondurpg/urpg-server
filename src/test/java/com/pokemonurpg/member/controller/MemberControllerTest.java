package com.pokemonurpg.member.controller;

import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MemberControllerTest {
    private final static String NAME = "TEST";
    private final static Integer DBID = 2342;
    private final static Boolean BOT = true;

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private Member member = new Member();

    @Test
    public void findAllNames() {
        List<String> names = new ArrayList<>();
        when(memberService.findNamesBy(NAME, BOT)).thenReturn(names);
        assertEquals(names, memberController.findNamesBy(NAME, BOT));
    }

    @Test
    public void findByName() {
        when(memberService.findByUsername(NAME)).thenReturn(member);
        assertEquals(member, memberController.findByName(NAME));
    }

    @Test
    public void create() {
        MemberInputDto input = new MemberInputDto();
        input.setUsername(NAME);
        when(memberService.create(input)).thenReturn(member);
        assertEquals(member, memberController.create(input));
    }

    @Test
    public void update() {
        MemberInputDto input = new MemberInputDto();
        input.setUsername(NAME);
        when(memberService.update(input, DBID)).thenReturn(member);
        assertEquals(member, memberController.update(input, DBID));
    }

}