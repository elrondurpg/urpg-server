package com.pokemonurpg.v2.domain.lib.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ConstraintViolationException extends IllegalStateException {
    private List<String> errors;
    public ConstraintViolationException(String error) {
        errors = new ArrayList<>();
        errors.add(error);
    }
}
