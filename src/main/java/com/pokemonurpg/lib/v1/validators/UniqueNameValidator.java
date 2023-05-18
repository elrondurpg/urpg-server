package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.annotations.UniqueName;
import com.pokemonurpg.lib.v1.model.NamedObject;
import com.pokemonurpg.lib.v1.request.UniquelyNamedInputDto;
import com.pokemonurpg.lib.v1.service.NamedObjectService;
import com.pokemonurpg.lib.v1.service.NamedObjectServiceFactory;
import com.pokemonurpg.lib.v1.service.RequestPathVariableService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, UniquelyNamedInputDto> {
    private Class type;

    @Resource
    private NamedObjectServiceFactory namedObjectServiceFactory;

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
                NamedObject existingObject = namedObjectService.findByNameExact(newName);

                if (existingObject != null) {
                    Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                    Integer objectDbid = existingObject.getDbid();
                    return requestDbid != null && objectDbid == (int) requestDbid;
                }
                else return true;
            }
            else return true;
        }
        else return true;
    }
}
