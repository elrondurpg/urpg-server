package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.configuration.v1.lib.input.NamedConfigurationInputDto;
import com.pokemonurpg.configuration.v1.lib.model.ConfigurationModel;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationServiceFactory;
import com.pokemonurpg.core.service.*;
import com.pokemonurpg.core.validation.annotation.UniqueName;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.ResolvableType;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, NamedConfigurationInputDto> {
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
    public boolean isValid(NamedConfigurationInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        if (isInputTestable(input)) {
            NamedConfigurationService<?, ?> service = getService(input.getClass());
            ConfigurationModel existingObject = service.findByNameExact(input.getName());
            valid = existingObject == null || doesRequestDbidMatch(existingObject.getDbid());
        }
        
        return valid;
    }

    private boolean isInputTestable(NamedConfigurationInputDto input) {
        return input != null && input.getName() != null;
    }

    private NamedConfigurationService<?, ?> getService(Class<? extends NamedConfigurationInputDto> dtoClass) {
        return (NamedConfigurationService<?, ?>) 
            beanFactory.getBeanProvider(getResolvableTypeForServiceClass(dtoClass)).getObject();
    }

    private ResolvableType getResolvableTypeForServiceClass(Class<? extends NamedConfigurationInputDto> dtoClass) {
        return ResolvableType.forClassWithGenerics(NamedConfigurationService.class, objectClass, dtoClass);
    }

    private boolean doesRequestDbidMatch(Integer dbid) {
        Integer requestDbid = requestPathVariableService.findIntByName("dbid");
        return requestDbid != null && dbid == (int) requestDbid;
    }
}
