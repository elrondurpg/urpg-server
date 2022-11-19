package com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.model.KnownEliteFourMember;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.repository.KnownEliteFourMemberRepository;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.test.RandomStringGenerator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class KnownEliteFourMemberServiceTest {
    private final static String OLD_NAME = RandomStringGenerator.generate();
    private final static String NEW_NAME = RandomStringGenerator.generate();
    private final static KnownEliteFourMember MODEL = new KnownEliteFourMember();

    @InjectMocks
    private KnownEliteFourMemberService service;

    @Mock
    private KnownEliteFourMemberRepository repository;

    @Captor
    ArgumentCaptor<KnownEliteFourMember> captor;

    @Test
    public void test_constructor() {
        assertEquals(repository, service.getRepository());
        assertEquals(KnownEliteFourMember.class, service.getModelClass());
    }

    @Test
    public void test_renameForMember() {
        Member member = new Member();
        member.setName(OLD_NAME);
        
        when(repository.findByName(OLD_NAME)).thenReturn(MODEL);

        service.renameForMember(member, NEW_NAME);
        assertEquals(NEW_NAME, MODEL.getName());
        verify(repository, times(1)).save(MODEL); 
    }

    @Test
    public void test_createForNameIfUnique_WhenNotUnique() {
        MODEL.setName(NEW_NAME);
        when(repository.findByName(NEW_NAME)).thenReturn(MODEL);
        service.createForNameIfUnique(NEW_NAME);
        assertEquals(NEW_NAME, MODEL.getName());
        verify(repository, times(0)).save(ArgumentMatchers.any());
    }

    @Test
    public void test_createForNameIfUnique_WhenUnique() {
        service.createForNameIfUnique(NEW_NAME);        
        verify(repository, times(1)).save(captor.capture());
        KnownEliteFourMember model = captor.getValue();
        assertEquals(NEW_NAME, model.getName());
    }

}