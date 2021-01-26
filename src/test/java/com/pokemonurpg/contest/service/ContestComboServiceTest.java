package com.pokemonurpg.contest.service;

import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.repository.AttackRepository;
import com.pokemonurpg.contest.input.ContestComboInputDto;
import com.pokemonurpg.contest.models.ContestCombo;
import com.pokemonurpg.contest.repository.ContestComboRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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

        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setContestType(CONTEST_TYPE);
        input.setDelete(true);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        when(contestComboRepository.findByFirstAttackAndSecondAttackAndIdContestType(FIRST_ATTACK, SECOND_ATTACK, CONTEST_TYPE)).thenReturn(contestCombo);

        contestComboService.update(FIRST_ATTACK, input);

        verify(contestComboRepository, times(1)).delete(contestCombo);
    }

    @Test
    public void updateWhenFormExistsAndDeleteIsFalse() {
        ContestCombo contestCombo = mock(ContestCombo.class);

        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setContestType(CONTEST_TYPE);
        input.setDelete(false);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        when(contestComboRepository.findByFirstAttackAndSecondAttackAndIdContestType(FIRST_ATTACK, SECOND_ATTACK, CONTEST_TYPE)).thenReturn(contestCombo);

        contestComboService.update(FIRST_ATTACK, input);

        verify(contestCombo, times(1)).update(input);
        verify(contestComboRepository, times(1)).save(contestCombo);
    }

    @Test
    public void createWhenFormDoesNotExist() {
        ContestComboInputDto input = new ContestComboInputDto();
        input.setSecondAttack(SECOND_ATTACK_NAME);
        input.setContestType(CONTEST_TYPE);

        when(attackRepository.findByName(SECOND_ATTACK_NAME)).thenReturn(SECOND_ATTACK);
        contestComboService.update(FIRST_ATTACK, input);

        verify(contestComboRepository, times(1)).save(Matchers.any());
    }
}