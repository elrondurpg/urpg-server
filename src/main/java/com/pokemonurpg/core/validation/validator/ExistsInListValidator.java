package com.pokemonurpg.core.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExistsInListValidator implements UrpgValidator {
    private List<String> validInputs = new ArrayList<>();

    public ExistsInListValidator (String... validInputs) {
        this.validInputs.addAll(Arrays.asList(validInputs));
    }

    public boolean isValid(Object input) {
        if (input instanceof String) {
            return validInputs.contains((String) input);
        }
        return false;
    }

    public List<String> getValidInputs() {
        return validInputs;
    }
}
