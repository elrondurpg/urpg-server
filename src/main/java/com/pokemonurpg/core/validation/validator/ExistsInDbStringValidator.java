package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.core.validation.annotation.ExistsInDb;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;

public class ExistsInDbStringValidator extends ExistsInDbValidator<String> {
    private Class type;

    @Resource
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Resource
    private BeanFactory beanFactory; 

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
            if (namedObjectService == null) {
                NamedConfigurationService<?, ?> service = getService();
                Object obj = service.findByNameExact(input);
                return obj != null;
            }
            else {
                Object obj = namedObjectService.findByNameExact(input);
                return obj != null;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private NamedConfigurationService<?, ?> getService() {
        ObjectProvider<NamedConfigurationService> op = beanFactory.getBeanProvider(NamedConfigurationService.class);
        return op.stream().filter(service -> service.getModelClass() == type).findFirst().get();
    }
}
