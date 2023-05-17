package com.pokemonurpg.lib.validation.validator;

import com.pokemonurpg.lib.service.IndexedObjectService;
import com.pokemonurpg.lib.service.IndexedObjectServiceFactory;
import com.pokemonurpg.lib.validation.annotation.ExistsInDb;

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
            IndexedObjectService indexedObjectService = indexedObjectServiceFactory.getServiceForClass(type);
            Object obj = indexedObjectService.findByDbid(input);
            return obj != null;
        } catch (Exception e) {
            return false;
        }
    }
}