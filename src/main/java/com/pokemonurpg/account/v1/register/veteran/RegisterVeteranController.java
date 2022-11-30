package com.pokemonurpg.account.v1.register.veteran;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerController;
import com.pokemonurpg.account.v1.register.common.RegisterPlayerRequest;
import com.pokemonurpg.account.v1.register.common.RegisterPlayerResponse;

@RestController
@RequestMapping("/registration/v1/veteran")
@CrossOrigin
@Validated
public class RegisterVeteranController extends RegisterPlayerController<RegisterPlayerRequest, RegisterPlayerResponse> {
    
}
