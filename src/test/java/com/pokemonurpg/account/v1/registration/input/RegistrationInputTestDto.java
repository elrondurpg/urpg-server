package com.pokemonurpg.account.v1.registration.input;

import com.pokemonurpg.account.v1.register.RegisterPlayerDto;
import com.pokemonurpg.test.RandomStringGenerator;

public class RegistrationInputTestDto extends RegisterPlayerDto {
    public RegistrationInputTestDto() {
        setCode(RandomStringGenerator.generate());
        setName(RandomStringGenerator.generate());
        setStarter(new StarterPokemonInputTestDto());
    }
}
