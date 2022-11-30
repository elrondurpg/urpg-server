package com.pokemonurpg.account.v1.register.returningplayer;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.RegisterPlayerController;
import com.pokemonurpg.account.v1.register.RegisterPlayerDto;

@RestController
@RequestMapping("/registration/v1/returning-player")
@CrossOrigin
@Validated
public class RegisterReturningPlayerController extends RegisterPlayerController<RegisterReturningPlayerHandler, RegisterPlayerDto> {
    
}
