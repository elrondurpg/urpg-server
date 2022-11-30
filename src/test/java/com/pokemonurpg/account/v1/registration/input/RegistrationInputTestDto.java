package com.pokemonurpg.account.v1.registration.input;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerInputDto;
import com.pokemonurpg.test.RandomStringGenerator;

public class RegistrationInputTestDto extends RegisterPlayerInputDto {
    public RegistrationInputTestDto() {
        setCode(RandomStringGenerator.generate());
        setName(RandomStringGenerator.generate());
        setStarter(new StarterPokemonInputTestDto());
    }
}
