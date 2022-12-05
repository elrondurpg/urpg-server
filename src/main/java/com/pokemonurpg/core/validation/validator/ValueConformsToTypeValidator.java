package com.pokemonurpg.core.validation.validator;

import com.pokemonurpg.core.input.UniquelyNamedInputDto;
import com.pokemonurpg.core.model.NamedObject;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.core.service.NamedObjectServiceFactory;
import com.pokemonurpg.core.service.RequestPathVariableService;
import com.pokemonurpg.core.validation.annotation.UniqueName;
import com.pokemonurpg.core.validation.annotation.ValueConformsToType;
import com.pokemonurpg.configuration.v1.site.flag.input.FlagInputDto;
import com.pokemonurpg.entities.v1.site.Flag;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueConformsToTypeValidator implements ConstraintValidator<ValueConformsToType, FlagInputDto> {

    @Resource
    private FlagService flagService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public void initialize(ValueConformsToType constraint) {

    }

    @Override
    public boolean isValid(FlagInputDto input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            String type = input.getType();
            String value = input.getValue();
            if (type != null || value != null) {

                if (type == null || value == null) {
                    Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                    Flag flag = flagService.findByDbid(requestDbid);

                    if (type == null) type = flag.getType();
                    if (value == null) value = flag.getValue();
                }

                return conforms(type, value);
            }
            else return true;
        }
        else return true;
    }

    public boolean conforms(String type, String value) {
        if (type == null || value == null) return true;

        if ("BOOLEAN".equalsIgnoreCase(type)) {
            return "TRUE".equalsIgnoreCase(value) || "FALSE".equalsIgnoreCase(value);
        }

        else if ("INTEGER".equalsIgnoreCase(type)) {
            try {
                Integer.parseInt(value);
            }
            catch (Exception e) {
                return false;
            }
        }

        else if ("DOUBLE".equalsIgnoreCase(type)) {
            try {
                Double.parseDouble(value);
            }
            catch (Exception e) {
                return false;
            }
        }

        return true;
    }
}
