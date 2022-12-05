package com.pokemonurpg.configuration.v1.attack.attack.list;

import org.springframework.beans.factory.annotation.Autowired;

import com.pokemonurpg.configuration.v1.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v1.shared.handler.ListHandler;
import com.pokemonurpg.configuration.v1.shared.request.JpaPageableFactory;
import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.attack.AttackRepository;

public class ListAttackHandler extends ListHandler<Attack, ListAttackRequest, ListAttackResponse> {
    @Autowired
    public ListAttackHandler(AttackRepository repository, JpaPageableFactory pageableFactory) {
        super(repository, pageableFactory);
    }
}
