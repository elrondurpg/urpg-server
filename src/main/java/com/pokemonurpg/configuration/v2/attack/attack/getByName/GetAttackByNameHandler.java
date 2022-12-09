package com.pokemonurpg.configuration.v2.attack.attack.getByName;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v2.attack.attack.shared.view.GetAttackResponse;
import com.pokemonurpg.configuration.v2.shared.handler.GetByNameHandler;
import com.pokemonurpg.entities.v1.attack.Attack;
import com.pokemonurpg.entities.v1.attack.AttackRepository;

@Service
public class GetAttackByNameHandler extends GetByNameHandler<Attack, GetAttackResponse> {
    public GetAttackByNameHandler(AttackRepository repository) {
        super(repository);
    }
}
