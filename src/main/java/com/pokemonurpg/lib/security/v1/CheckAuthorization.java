package com.pokemonurpg.lib.security.v1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CheckAuthorization {
    public AuthorizationType authorizationType() default AuthorizationType.DENY_ALL; 
}
