package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.IndexedObjectService;
import com.pokemonurpg.core.service.IndexedObjectServiceFactory;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class ExistsInDbIntValidator extends ExistsInDbValidator<Integer> {
    private Class type;

    @Resource
    private IndexedObjectServiceFactory indexedObjectServiceFactory;

    public Class getType() {
        return type;
    }

    @Override
    public void initialize(ExistsInDb constraint) {
        this.type = constraint.type();
    }

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext context) {
        try {
            if (input == null) return true;
            IndexedObjectService namedObjectService = indexedObjectServiceFactory.getServiceForClass(type);
            Object obj = namedObjectService.findByDbid(input);
            return obj != null;
        } catch (Exception e) {
            return false;
        }
    }
}
