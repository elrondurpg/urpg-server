package com.pokemonurpg.account.v1.register;

import com.pokemonurpg.core.validation.ObjectCreation;
import com.pokemonurpg.core.validation.annotation.DoesNotConflictWithKnownGymLeader;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegisterPlayerDto {
    @NotEmpty
    private String code;

    @NotEmpty
    @DoesNotConflictWithKnownGymLeader(groups = {ObjectCreation.class })
    @Size(min = 3, max = 30)
    private String name;
}
