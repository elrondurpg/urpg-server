package com.pokemonurpg.core.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchesValidator implements UrpgValidator {
    private Pattern[] validPatterns;

    public MatchesValidator (Pattern... validPatterns) {
        this.validPatterns = validPatterns;
    }

    @Override
    public boolean isValid(Object input) {
        if (input instanceof String) {
            String s = (String) input;
            for (Pattern p : validPatterns) {
                Matcher m = p.matcher(s);
                if (m.find()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
