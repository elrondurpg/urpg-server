package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
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

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.ResolvableType;

public class UniqueNameValidatorOld implements ConstraintValidator<UniqueName, UniquelyNamedInputDto> {
    private Class<? extends ConfigurationModel> objectClass;

    @Resource
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Resource
    private NamedConfigurationServiceFactory namedConfigurationServiceFactory;

    @Resource
    private BeanFactory beanFactory;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    public Class<? extends ConfigurationModel> getType() {
        return objectClass;
    }

    @Override
    public void initialize(UniqueName constraint) {
        this.objectClass = constraint.type();
    }

    @Override
    public boolean isValid(UniquelyNamedInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if (isInputTestable(input)) {
            valid = isValidAccordingToOldParadigm(input);
        }
        
        return valid;
    }

    private boolean isInputTestable(UniquelyNamedInputDto input) {
        return input != null && input.getName() != null;
    }

    private boolean isValidAccordingToOldParadigm(UniquelyNamedInputDto input) {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(objectClass);
        NamedObject existingObject = service.findByNameExact(input.getName());
        return existingObject == null || doesRequestDbidMatch(existingObject.getDbid());
    }

    private boolean doesRequestDbidMatch(Integer dbid) {
        Integer requestDbid = requestPathVariableService.findIntByName("dbid");
        return requestDbid != null && dbid == (int) requestDbid;
    }
}
