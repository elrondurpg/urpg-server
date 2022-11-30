package com.pokemonurpg.account.v1.register.newplayer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.RegisterPlayerController;

@RestController
@RequestMapping("/registration/v1/new-player")
@CrossOrigin
@Validated
public class RegisterNewPlayerController extends RegisterPlayerController<RegisterNewPlayerHandler, RegisterNewPlayerDto> {
    
}
