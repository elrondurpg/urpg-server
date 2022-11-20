package com.pokemonurpg.configuration.v1.member.member.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService service;

    @Mock
    private MemberRepository repository;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(Member.class, service.getModelClass());
    }

}