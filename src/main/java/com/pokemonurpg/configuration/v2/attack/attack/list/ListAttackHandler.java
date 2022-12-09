package com.pokemonurpg.configuration.v2.attack.attack.list;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.ListAttackResponse;
import com.pokemonurpg.configuration.v2.shared.handler.ListHandler;
import com.pokemonurpg.configuration.v2.shared.request.JpaPageableFactory;
import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.attack.AttackRepository;

@Service
public class ListAttackHandler extends ListHandler<Attack, ListAttackRequest, ListAttackResponse> {

    public ListAttackHandler(AttackRepository repository, JpaPageableFactory pageableFactory) {
        super(repository, pageableFactory);
    }
    
}
