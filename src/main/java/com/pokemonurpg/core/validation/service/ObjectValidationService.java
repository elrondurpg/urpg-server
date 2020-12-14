package com.pokemonurpg.core.validation;

import com.pokemonurpg.core.validation.annotation.Nullable;
import com.pokemonurpg.core.validation.validator.UrpgValidator;
import com.pokemonurpg.core.validation.validator.UrpgValidatorFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Service
public class ValidationService {
    @Resource
    private UrpgValidatorFactory urpgValidatorFactory;

    public boolean validate(Object input, boolean nullable) throws IllegalAccessException {
        return input != null && allFieldsAreValid(input, nullable);
    }

    public boolean allFieldsAreValid(Object input, boolean nullable) throws IllegalAccessException {
        Class<?> clazz = input.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (!(fieldIsNullableAndNull(input, field) || fieldIsValidForAllAnnotations(input, field, nullable)))
            {
                return false;
            }
        }
        return true;
    }

    public boolean fieldIsNullableAndNull(Object input, Field field) throws IllegalAccessException {
        Object valueOfField = field.get(input);
        return field.getAnnotationsByType(Nullable.class).length > 0 && valueOfField == null;
    }

    public boolean fieldIsValidForAllAnnotations(Object input, Field field, boolean nullable) throws IllegalAccessException {
        Object valueOfField = field.get(input);
        for (Annotation annotation : field.getAnnotations()) {
            UrpgValidator validator = urpgValidatorFactory.getValidator(annotation);
            if (validator != null) {
                if ((nullable || valueOfField != null) && !validator.isValid(valueOfField))
                    return false;
            }
        }
        return true;
    }
}
