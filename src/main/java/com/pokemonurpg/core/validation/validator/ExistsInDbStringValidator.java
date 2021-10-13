package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

public class ExistsInDbStringValidator extends ExistsInDbValidator<String> {
    private Class type;

    @Resource
    private NamedObjectServiceFactory namedObjectServiceFactory;

    public Class getType() {
        return type;
    }

    @Override
    public void initialize(ExistsInDb constraint) {
        this.type = constraint.type();
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        try {
            if (input == null) return true;
            NamedObjectService namedObjectService = namedObjectServiceFactory.getServiceForClass(type);
            Object obj = namedObjectService.findByNameExact(input);
            return obj != null;
        } catch (Exception e) {
            return false;
        }
    }
}
