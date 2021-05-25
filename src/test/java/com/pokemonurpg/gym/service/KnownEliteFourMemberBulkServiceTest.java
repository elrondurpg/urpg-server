package com.pokemonurpg.gym.service;

import com.pokemonurpg.gym.input.KnownEliteFourMemberBulkInputDto;
import com.pokemonurpg.gym.input.KnownEliteFourMemberInputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KnownEliteFourMemberBulkServiceTest {
    private final static List<String> ELITE_FOUR_MEMBER_NAMES = new ArrayList<>();

    @InjectMocks
    private KnownEliteFourMemberBulkService knownEliteFourMemberBulkService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Test
    public void update() {
        KnownEliteFourMemberInputDto input1 = new KnownEliteFourMemberInputDto();
        KnownEliteFourMemberInputDto input2 = new KnownEliteFourMemberInputDto();
        List<KnownEliteFourMemberInputDto> list = Arrays.asList(input1, input2);

        KnownEliteFourMemberBulkInputDto bulkInput = new KnownEliteFourMemberBulkInputDto();
        bulkInput.setEliteFourMembers(list);

        when(knownEliteFourMemberService.findAllNames()).thenReturn(ELITE_FOUR_MEMBER_NAMES);

        List<String> leaderNames = knownEliteFourMemberBulkService.update(bulkInput);
        assertEquals(ELITE_FOUR_MEMBER_NAMES, leaderNames);
        verify(knownEliteFourMemberService).update(input1);
        verify(knownEliteFourMemberService).update(input2);
    }

}