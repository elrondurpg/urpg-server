package com.pokemonurpg.configuration.v1.shared.request;

import org.springframework.data.domain.Pageable;

import lombok.Getter;

@Getter
public class JpaPageableFactoryFake extends JpaPageableFactory {
    public final static Pageable PAGEABLE = Pageable.unpaged();

    private ListRequest requestArg;

    public Pageable fromRequest(ListRequest request) {
        requestArg = request;
        return PAGEABLE;
    }
}
