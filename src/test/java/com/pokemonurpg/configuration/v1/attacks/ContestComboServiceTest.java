package com.pokemonurpg.configuration.v1.attacks;

import com.pokemonurpg.entities.v1.Attack;
import com.pokemonurpg.entities.v1.ContestGeneration;
import com.pokemonurpg.infrastructure.v1.data.jpa.AttackRepository;
import com.pokemonurpg.entities.v1.ContestCombo;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestComboRepository;
import com.pokemonurpg.infrastructure.v1.data.jpa.ContestGenerationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContestComboServiceTest {
    private final static Attack FIRST_ATTACK = new Attack();
    private final static Attack SECOND_ATTACK = new Attack();
    private final static String SECOND_ATTACK_NAME = "SECOND_ATTACK_NAME";
    private final static String CONTEST_TYPE = "CONTEST_TYPE";
    private static final Integer GENERATION_DBID = 432;

    @InjectMocks
    private ContestComboService contestComboService;

    @Mock
    private ContestComboRepository contestComboRepository;

    @Mock
    private AttackRepository attackRepository;

    @Mock
    private ContestGenerationRepository contestGenerationRepository;

    @Test
    public void deleteWhenFormExistsAndDeleteIsTrue() {
        ContestCombo contestCombo = mock(ContestCombo.class);
/* 
        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setContestType(CONTEST_TYPE);
        input.setDelete(true);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        when(contestComboRepository.findByFirstAttackAndSecondAttackAndIdContestType(FIRST_ATTACK, SECOND_ATTACK, CONTEST_TYPE)).thenReturn(contestCombo);

        contestComboService.update(FIRST_ATTACK, input);

        verify(contestComboRepository, times(1)).delete(contestCombo);*/
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        ContestCombo contestCombo = mock(ContestCombo.class);

       /* ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setContestType(CONTEST_TYPE);
        input.setDelete(false);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        when(contestComboRepository.findByFirstAttackAndSecondAttackAndIdContestType(FIRST_ATTACK, SECOND_ATTACK, CONTEST_TYPE)).thenReturn(contestCombo);

        contestComboService.update(FIRST_ATTACK, input);

        verify(contestCombo, times(1)).update(input);
        verify(contestComboRepository, times(1)).save(contestCombo);*/
    }

    @Test
    public void createWhenFormDoesNotExist() {
        ContestComboRequest input = new ContestComboRequest();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setGeneration(CONTEST_TYPE);

        ContestGeneration generation = new ContestGeneration();
        generation.setDbid(GENERATION_DBID);

        when(contestGenerationRepository.findByName(CONTEST_TYPE)).thenReturn(generation);
        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        contestComboService.update(FIRST_ATTACK, input);
    }
}