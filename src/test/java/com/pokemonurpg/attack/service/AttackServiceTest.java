package com.pokemonurpg.attack.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.input.AttackInputDto;
import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.attack.repository.AttackCategoryRepository;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.attack.repository.AttackTargetTypeRepository;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.repository.ContestAttributeRepository;
import com.pokemonurpg.contest.repository.DPPContestMoveTypeRepository;
import com.pokemonurpg.contest.repository.ORASContestMoveTypeRepository;
import com.pokemonurpg.contest.repository.RSEContestMoveTypeRepository;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.repository.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AttackServiceTest {
    private final static List<String> ALL_NAMES = new ArrayList<>();
    private final static Integer DBID = 3298407;
    private final static String NAME = "NAME";
    private final static Attack ATTACK = new Attack();
    private final static Type TYPE = new Type();
    private final static String TYPE_NAME = "TYPE";
    private final static AttackCategory CATEGORY = new AttackCategory();
    private final static String CATEGORY_NAME = "CATEGORY";
    private final static AttackTargetType TARGET = new AttackTargetType();
    private final static RSEContestMoveType RSE_CONTEST_MOVE_TYPE = new RSEContestMoveType();
    private final static ContestAttribute RSE_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static ORASContestMoveType ORAS_CONTEST_MOVE_TYPE = new ORASContestMoveType();
    private final static ContestAttribute ORAS_CONTEST_ATTRIBUTE = new ContestAttribute();
    private final static DPPContestMoveType DPP_CONTEST_MOVE_TYPE = new DPPContestMoveType();
    private final static ContestAttribute DPP_CONTEST_ATTRIBUTE = new ContestAttribute();
    private static final String TARGET_NAME = "TARGET";
    private static final String RSE_CONTEST_MOVE_TYPE_NAME = "RSE_CONTEST_MOVE_TYPE";
    private static final String RSE_CONTEST_ATTRIBUTE_NAME = "RSE_CONTEST_ATTRIBUTE";
    private static final String DPP_CONTEST_MOVE_TYPE_NAME = "DPP_CONTEST_MOVE_TYPE";
    private static final String DPP_CONTEST_ATTRIBUTE_NAME = "DPP_CONTEST_ATTRIBUTE";
    private static final String ORAS_CONTEST_MOVE_TYPE_NAME = "ORAS_CONTEST_MOVE_TYPE";
    private static final String ORAS_CONTEST_ATTRIBUTE_NAME = "ORAS_CONTEST_ATTRIBUTE";

    @InjectMocks
    private AttackService attackService;

    @Mock
    private AttackRepository attackRepository;

    @Mock
    private AttackCategoryRepository attackCategoryRepository;

    @Mock
    private AttackTargetTypeRepository attackTargetTypeRepository;

    @Mock
    private TypeRepository typeRepository;

    @Mock
    private ContestAttributeRepository contestAttributeRepository;

    @Mock
    private RSEContestMoveTypeRepository rseContestMoveTypeRepository;

    @Mock
    private ORASContestMoveTypeRepository orasContestMoveTypeRepository;

    @Mock
    private DPPContestMoveTypeRepository dppContestMoveTypeRepository;

    @Test
    public void findAllNames() {
        when(attackRepository.findAllNames()).thenReturn(ALL_NAMES);
        assertEquals(ALL_NAMES, attackService.findAllNames());
    }

    @Test
    public void findByNameExists() {
        when(attackRepository.findByName(NAME)).thenReturn(ATTACK);
        assertEquals(ATTACK, attackService.findByName(NAME));
    }

    @Test
    public void findByNamePartialMatch() {
        when(attackRepository.findByName(NAME)).thenReturn(null);
        when(attackRepository.findFirstByNameStartingWith(NAME)).thenReturn(ATTACK);
        assertEquals(ATTACK, attackService.findByName(NAME));
    }

    @Test
    public void findByNameFails() {
        when(attackRepository.findByName(NAME)).thenReturn(null);
        when(attackRepository.findFirstByNameStartingWith(NAME)).thenReturn(null);
        assertNull(attackService.findByName(NAME));
    }

    @Test
    public void create() {
        AttackInputDto input = createAttackInputDto();
        Attack attack = attackService.create(input);
        assertEquals(TYPE, attack.getType());
        assertEquals(CATEGORY, attack.getCategory());
        assertEquals(TARGET, attack.getTarget());
        assertEquals(RSE_CONTEST_ATTRIBUTE, attack.getRseContestAttribute());
        assertEquals(RSE_CONTEST_MOVE_TYPE, attack.getRseContestMoveType());
        assertEquals(DPP_CONTEST_ATTRIBUTE, attack.getDppContestAttribute());
        assertEquals(DPP_CONTEST_MOVE_TYPE, attack.getDppContestMoveType());
        assertEquals(ORAS_CONTEST_ATTRIBUTE, attack.getOrasContestAttribute());
        assertEquals(ORAS_CONTEST_MOVE_TYPE, attack.getOrasContestMoveType());
    }

    @Test
    public void update() {
        AttackInputDto input = createAttackInputDto();
        when(attackRepository.findByDbid(DBID)).thenReturn(new Attack());

        Attack attack = attackService.update(input, DBID);
        verify(attackRepository, times(1)).save(attack);
        assertEquals(TYPE, attack.getType());
        assertEquals(CATEGORY, attack.getCategory());
        assertEquals(TARGET, attack.getTarget());
        assertEquals(RSE_CONTEST_ATTRIBUTE, attack.getRseContestAttribute());
        assertEquals(RSE_CONTEST_MOVE_TYPE, attack.getRseContestMoveType());
        assertEquals(DPP_CONTEST_ATTRIBUTE, attack.getDppContestAttribute());
        assertEquals(DPP_CONTEST_MOVE_TYPE, attack.getDppContestMoveType());
        assertEquals(ORAS_CONTEST_ATTRIBUTE, attack.getOrasContestAttribute());
        assertEquals(ORAS_CONTEST_MOVE_TYPE, attack.getOrasContestMoveType());
    }

    @Test
    public void updateFailsWhenExistingAttackNotFound() {
        AttackInputDto input = createAttackInputDto();
        when(attackRepository.findByDbid(DBID)).thenReturn(null);
        Attack attack = attackService.update(input, DBID);
        assertNull(attack);
        verify(attackRepository, times(0)).save(Matchers.any(Attack.class));

    }

    public AttackInputDto createAttackInputDto() {
        AttackInputDto input = mock(AttackInputDto.class);
        when(input.getType()).thenReturn(TYPE_NAME);
        when(input.getCategory()).thenReturn(CATEGORY_NAME);
        when(input.getTarget()).thenReturn(TARGET_NAME);
        when(input.getRseContestMoveType()).thenReturn(RSE_CONTEST_MOVE_TYPE_NAME);
        when(input.getRseContestAttribute()).thenReturn(RSE_CONTEST_ATTRIBUTE_NAME);
        when(input.getDppContestMoveType()).thenReturn(DPP_CONTEST_MOVE_TYPE_NAME);
        when(input.getDppContestAttribute()).thenReturn(DPP_CONTEST_ATTRIBUTE_NAME);
        when(input.getOrasContestMoveType()).thenReturn(ORAS_CONTEST_MOVE_TYPE_NAME);
        when(input.getOrasContestAttribute()).thenReturn(ORAS_CONTEST_ATTRIBUTE_NAME);

        when(typeRepository.findByName(TYPE_NAME)).thenReturn(TYPE);
        when(attackCategoryRepository.findByName(CATEGORY_NAME)).thenReturn(CATEGORY);
        when(attackTargetTypeRepository.findByName(TARGET_NAME)).thenReturn(TARGET);
        when(rseContestMoveTypeRepository.findByName(RSE_CONTEST_MOVE_TYPE_NAME)).thenReturn(RSE_CONTEST_MOVE_TYPE);
        when(contestAttributeRepository.findByName(RSE_CONTEST_ATTRIBUTE_NAME)).thenReturn(RSE_CONTEST_ATTRIBUTE);
        when(dppContestMoveTypeRepository.findByName(DPP_CONTEST_MOVE_TYPE_NAME)).thenReturn(DPP_CONTEST_MOVE_TYPE);
        when(contestAttributeRepository.findByName(DPP_CONTEST_ATTRIBUTE_NAME)).thenReturn(DPP_CONTEST_ATTRIBUTE);
        when(orasContestMoveTypeRepository.findByName(ORAS_CONTEST_MOVE_TYPE_NAME)).thenReturn(ORAS_CONTEST_MOVE_TYPE);
        when(contestAttributeRepository.findByName(ORAS_CONTEST_ATTRIBUTE_NAME)).thenReturn(ORAS_CONTEST_ATTRIBUTE);

        return input;
    }
}