package com.pokemonurpg.configuration.v1.attack.attack;

import com.pokemonurpg.configuration.v1.attack.AttackSubdomainViews;

public interface AttackViews extends AttackSubdomainViews {
    public interface Id extends AttackViews {

    }

    public interface Brief extends Id {

    }

    public interface Full extends Brief {
        
    }
}
