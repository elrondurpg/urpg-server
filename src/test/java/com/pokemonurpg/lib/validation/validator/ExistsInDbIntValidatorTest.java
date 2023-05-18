package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.v1.service.IndexedObjectServiceFactory;
import com.pokemonurpg.lib.v1.annotations.ExistsInDb;
import com.pokemonurpg.entities.v1.EarnedRibbon;
import com.pokemonurpg.lib.v1.validators.ExistsInDbIntValidator;
import com.pokemonurpg.stats.v1.EarnedRibbonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

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
    public void returnsFalseOnException() {
        assertFalse(existsInDbValidator.isValid(DBID, null));
    }
}