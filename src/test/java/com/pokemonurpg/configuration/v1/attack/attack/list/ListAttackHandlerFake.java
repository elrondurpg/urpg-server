package com.pokemonurpg.configuration.v1.attack.attack.list;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageImpl;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v1.shared.handler.ListInputBoundary;
import com.pokemonurpg.configuration.v1.shared.view.ListResponse;
import com.pokemonurpg.entities.v1.attack.Attack;

import lombok.Getter;

@Getter
public class ListAttackHandlerFake implements ListInputBoundary<ListAttackRequest, ListAttackResponse> 
{
    public final static List<Attack> ATTACKS = Collections.singletonList(new Attack());

    private ListAttackRequest requestArg;

    @Override
    public ListResponse<ListAttackResponse> getList(ListAttackRequest request) {
        requestArg = request;
        return new ListResponse<>(new PageImpl<>(ATTACKS));
    }
    
}
