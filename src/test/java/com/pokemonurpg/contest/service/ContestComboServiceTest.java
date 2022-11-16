package com.pokemonurpg.contest.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.configuration.v1.attack.attack.input.ContestComboInputDto;
import com.pokemonurpg.configuration.v1.attack.attack.model.ContestCombo;
import com.pokemonurpg.configuration.v1.attack.attack.repository.ContestComboRepository;
import com.pokemonurpg.configuration.v1.attack.attack.service.ContestComboService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContestComboServiceTest {
    private final static Attack FIRST_ATTACK = new Attack();
    private final static Attack SECOND_ATTACK = new Attack();
    private final static String SECOND_ATTACK_NAME = "SECOND_ATTACK_NAME";
    private final static String CONTEST_TYPE = "CONTEST_TYPE";

    @InjectMocks
    private ContestComboService contestComboService;

    @Mock
    private ContestComboRepository contestComboRepository;

    @Mock
    private AttackRepository attackRepository;

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
        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        //input.setContestType(CONTEST_TYPE);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        contestComboService.update(FIRST_ATTACK, input);

        verify(contestComboRepository, times(1)).save(ArgumentMatchers.any());
    }
}