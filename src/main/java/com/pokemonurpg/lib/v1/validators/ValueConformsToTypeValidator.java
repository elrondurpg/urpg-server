package com.pokemonurpg.lib.v1.validators;

import com.pokemonurpg.lib.v1.services.RequestPathVariableService;
import com.pokemonurpg.lib.v1.annotations.ValueConformsToType;
import com.pokemonurpg.configuration.v1.featureflags.FeatureFlagRequest;
import com.pokemonurpg.entities.v1.FeatureFlag;
import com.pokemonurpg.configuration.v1.featureflags.FeatureFlagService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueConformsToTypeValidator implements ConstraintValidator<ValueConformsToType, FeatureFlagRequest> {

    @Resource
    private FeatureFlagService featureFlagService;

    @Resource
    private RequestPathVariableService requestPathVariableService;

    @Override
    public void initialize(ValueConformsToType constraint) {

    }

    @Override
    public boolean isValid(FeatureFlagRequest input, ConstraintValidatorContext constraintValidatorContext) {
        if (input != null) {
            String type = input.getType();
            String value = input.getValue();
            if (type != null || value != null) {

                if (type == null || value == null) {
                    Integer requestDbid = requestPathVariableService.findIntByName("dbid");
                    FeatureFlag featureFlag = featureFlagService.findByDbid(requestDbid);

                    if (type == null) type = featureFlag.getType();
                    if (value == null) value = featureFlag.getValue();
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
