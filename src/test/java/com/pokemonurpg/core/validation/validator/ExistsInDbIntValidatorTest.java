package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.IndexedObjectServiceFactory;
import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExistsInDbIntValidatorTest {
    private static final Integer DBID = 4324;

    @InjectMocks
    private ExistsInDbIntValidator existsInDbValidator = new ExistsInDbIntValidator();

    @Mock
    private IndexedObjectServiceFactory indexedObjectServiceFactory;

    @Mock
    private EarnedRibbonService earnedRibbonService;

    @ExistsInDb(type = EarnedRibbon.class)
    private Integer dbid;

    @Test
    public void returnsTrueWhenNull() {
        assertTrue(existsInDbValidator.isValid(null, null));
    }

    @Test
    public void initialize() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("dbid").getAnnotation(ExistsInDb.class));
        assertEquals(EarnedRibbon.class, existsInDbValidator.getType());
    }

    @Test
    public void returnsFalseWhenUnique() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("dbid").getAnnotation(ExistsInDb.class));
        when(indexedObjectServiceFactory.getServiceForClass(EarnedRibbon.class)).thenReturn(earnedRibbonService);
        when(earnedRibbonService.findByDbid(DBID)).thenReturn(null);

        assertFalse(existsInDbValidator.isValid(DBID, null));
    }

    @Test
    public void returnsTrueWhenNotUnique() throws NoSuchFieldException {
        existsInDbValidator.initialize(this.getClass().getDeclaredField("dbid").getAnnotation(ExistsInDb.class));
        when(indexedObjectServiceFactory.getServiceForClass(EarnedRibbon.class)).thenReturn(earnedRibbonService);
        when(earnedRibbonService.findByDbid(DBID)).thenReturn(new EarnedRibbon());

        assertTrue(existsInDbValidator.isValid(DBID, null));
    }

    @Test
    public void returnsFalseOnException() {
        assertFalse(existsInDbValidator.isValid(DBID, null));
    }
}