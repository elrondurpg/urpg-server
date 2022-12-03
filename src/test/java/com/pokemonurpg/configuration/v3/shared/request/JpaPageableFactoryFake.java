package com.pokemonurpg.configuration.v3.shared.request;

import org.springframework.data.domain.Pageable;

import lombok.Getter;

@Getter
public class JpaPageableFactoryFake extends JpaPageableFactory {
    public final static Pageable PAGEABLE = Pageable.unpaged();

    private GetParams requestArg;

    public Pageable fromRequest(GetParams request) {
        requestArg = request;
        return PAGEABLE;
    }
}
