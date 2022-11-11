package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.IndexedObjectServiceFactory;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;
import com.pokemonurpg.stats.models.EarnedRibbon;
import com.pokemonurpg.stats.service.EarnedRibbonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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