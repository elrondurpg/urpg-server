package com.pokemonurpg.configuration.v1.attack.attack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pokemonurpg.attack.repository.AttackCategoryRepository;
import com.pokemonurpg.attack.repository.AttackTargetTypeRepository;
import com.pokemonurpg.configuration.v1.attack.attack.input.AttackInputDto;
import com.pokemonurpg.configuration.v1.attack.attack.input.AttackInputTestDto;
import com.pokemonurpg.configuration.v1.attack.attack.model.Attack;
import com.pokemonurpg.configuration.v1.attack.attack.repository.AttackRepository;
import com.pokemonurpg.configuration.v1.contest.attribute.repository.ContestAttributeRepository;
import com.pokemonurpg.configuration.v1.contest.oras.repository.OrasContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.contest.rse.repository.RseContestMoveTypeRepository;
import com.pokemonurpg.configuration.v1.pokemon.type.repository.TypeRepository;
import com.pokemonurpg.item.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class AttackServiceTest {

    @InjectMocks
    private AttackService service;

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
    private RseContestMoveTypeRepository rseContestMoveTypeRepository;

    @Mock
    private OrasContestMoveTypeRepository orasContestMoveTypeRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ContestComboService contestComboService;

    @Test
    public void test_updateBase() {
        AttackInputTestDto input = new AttackInputTestDto();
        Attack model = new Attack();
        service.updateBase(model, input);
        assert_updateBaseValid(model, input);
    }

    private void assert_updateBaseValid(Attack model, AttackInputDto input) {
        assertEquals(input.getName(), model.getName());
        assertEquals(input.getDescription(), model.getDescription());
        assertEquals(input.getPower(), model.getPower());
        assertEquals(input.getAccuracy(), model.getAccuracy());
        assertEquals(input.getPp(), model.getPp());
        assertEquals(input.getContact(), model.getContact());
        assertEquals(input.getSnatch(), model.getSnatch());
        assertEquals(input.getSubstitute(), model.getSubstitute());
        assertEquals(input.getSheerForce(), model.getSheerForce());
        assertEquals(input.getMagicCoat(), model.getMagicCoat());
    }

    private void setup_updateEmbeddedValues(AttackInputDto input) {
        when(typeRepository.findByName(input.getType())).thenReturn(AttackInputTestDto.TYPE);
        when(attackCategoryRepository.findByName(input.getCategory())).thenReturn(AttackInputTestDto.CATEGORY);
        when(attackTargetTypeRepository.findByName(input.getTarget())).thenReturn(AttackInputTestDto.TARGET);
        when(rseContestMoveTypeRepository.findByName(input.getRseContestMoveType())).thenReturn(AttackInputTestDto.RSE_MOVE_TYPE);
        when(contestAttributeRepository.findByName(input.getRseContestAttribute())).thenReturn(AttackInputTestDto.RSE_ATTR);
        when(orasContestMoveTypeRepository.findByName(input.getOrasContestMoveType())).thenReturn(AttackInputTestDto.ORAS_MOVE_TYPE);
        when(contestAttributeRepository.findByName(input.getOrasContestAttribute())).thenReturn(AttackInputTestDto.ORAS_ATTR);
        when(itemRepository.findByName(input.getTm())).thenReturn(AttackInputTestDto.TM);
    }

    @Test
    public void test_updateEmbeddedValues() {
        AttackInputTestDto input = new AttackInputTestDto();
        setup_updateEmbeddedValues(input);
        Attack model = new Attack();
        service.updateEmbeddedValues(model, input);
        assert_updateEmbeddedValuesValid(model);
    }

    private void assert_updateEmbeddedValuesValid(Attack model) {
        assertEquals(AttackInputTestDto.TYPE, model.getType());
        assertEquals(AttackInputTestDto.CATEGORY, model.getCategory());
        assertEquals(AttackInputTestDto.TARGET, model.getTarget());
        assertEquals(AttackInputTestDto.RSE_MOVE_TYPE, model.getRseContestMoveType());
        assertEquals(AttackInputTestDto.RSE_ATTR, model.getRseContestAttribute());
        assertEquals(AttackInputTestDto.ORAS_MOVE_TYPE, model.getOrasContestMoveType());
        assertEquals(AttackInputTestDto.ORAS_ATTR, model.getOrasContestAttribute());
        assertEquals(AttackInputTestDto.TM, model.getTm());
    }

    @Test
    public void test_updateAssociatedValues() {
        AttackInputTestDto input = new AttackInputTestDto();
        Attack model = new Attack();
        service.updateAssociatedValues(model, input);
        assert_updateAssociatedValuesValid(model, input);
    }

    private void assert_updateAssociatedValuesValid(Attack model, AttackInputDto input) {
        input.getContestCombos().forEach(combo -> {
            verify(contestComboService, times(1)).update(model, combo);
        });
    }
}