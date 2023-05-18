package com.pokemonurpg.stats.service;

import com.pokemonurpg.entities.v1.Section;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.entities.v1.LegendaryProgress;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LegendaryProgressServiceTest {
    private final static Integer DBID = 432;
    private final static LegendaryProgress LEGENDARY_PROGRESS = mock(LegendaryProgress.class);
    private final static Member MEMBER = mock(Member.class);
    private final static String SECTION_NAME = "SECTION_NAME";
    private final static Section SECTION = mock(Section.class);
/*
    @Captor
    private ArgumentCaptor<LegendaryProgress> captor;

    @InjectMocks
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private LegendaryProgressRepository legendaryProgressRepository;

    @Mock
    private SectionService sectionService;

    @Test
    public void findByDbid() {
        when(legendaryProgressRepository.findByDbid(DBID)).thenReturn(LEGENDARY_PROGRESS);
        assertEquals(LEGENDARY_PROGRESS, legendaryProgressService.findByDbid(DBID));
    }

    @Test
    public void createsNewRecord() {
        LegendaryProgressInputDto input = new LegendaryProgressInputDto();
        input.setDbid(DBID);
        input.setSection(SECTION_NAME);

        when(legendaryProgressRepository.findByDbid(DBID)).thenReturn(null);
        when(sectionService.findByName(SECTION_NAME)).thenReturn(SECTION);

        legendaryProgressService.update(input, MEMBER);

        verify(legendaryProgressRepository).save(captor.capture());
        assertEquals(MEMBER, captor.getValue().getTrainer());
        assertEquals(SECTION, captor.getValue().getSection());
    }

    @Test
    public void updatesExistingRecord() {
        LegendaryProgressInputDto input = new LegendaryProgressInputDto();
        input.setDbid(DBID);

        when(legendaryProgressRepository.findByDbid(DBID)).thenReturn(LEGENDARY_PROGRESS);

        legendaryProgressService.update(input, MEMBER);

        verify(LEGENDARY_PROGRESS, times(1)).update(input);
        verify(legendaryProgressRepository, times(1)).save(LEGENDARY_PROGRESS);
    }

    @Test
    public void deletesExistingRecord() {
        LegendaryProgressInputDto input = new LegendaryProgressInputDto();
        input.setDbid(DBID);
        input.setDelete(true);

        when(legendaryProgressRepository.findByDbid(DBID)).thenReturn(LEGENDARY_PROGRESS);

        legendaryProgressService.update(input, MEMBER);

        verify(legendaryProgressRepository, times(1)).delete(LEGENDARY_PROGRESS);
    }
    */
}