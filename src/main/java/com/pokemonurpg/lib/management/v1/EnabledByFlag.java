package com.pokemonurpg.lib.management.v1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnabledByFlag {
    public String flag() default ""; 
    public String message() default "The requested operation is not enabled at this time.";
}
