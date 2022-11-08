package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationServiceFactory;
import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.core.service.*;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, UniquelyNamedInputDto> {
    private Class type;

    @Resource
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Resource
    private NamedConfigurationServiceFactory namedConfigurationServiceFactory;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    public Class getType() {
        return type;
    }
    @Override
    public void initialize(UniqueName constraint) {
        this.type = constraint.type();
    }

    @Override
    public boolean isValid(UniquelyNamedInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            String newName = input.getName();
            if (newName != null) {
                NamedObjectService namedObjectService = namedObjectServiceFactory.getServiceForClass(getType());
                if (namedObjectService == null) {
                    NamedConfigurationService<?, ?> namedConfigurationService = namedConfigurationServiceFactory.getServiceForClass(getType());
                    ConfigurationModel existingObject = namedConfigurationService.findByNameExact(newName);

                    if (existingObject != null) {
                        Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                        Integer objectDbid = existingObject.getDbid();
                        return requestDbid != null && objectDbid == (int) requestDbid;
                    }
                    else return true;
                }
                else {
                    NamedObject existingObject = namedObjectService.findByNameExact(newName);

                    if (existingObject != null) {
                        Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                        Integer objectDbid = existingObject.getDbid();
                        return requestDbid != null && objectDbid == (int) requestDbid;
                    }
                    else return true;
                }
            }
            else return true;
        }
        else return true;
    }
}
