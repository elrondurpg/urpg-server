package com.pokemonurpg.account.v1.register.beginner;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.common.RegisterPlayerController;

@RestController
@RequestMapping("/registration/v1/beginner")
@CrossOrigin
@Validated
public class RegisterBeginnerController extends RegisterPlayerController<RegisterBeginnerRequest, RegisterBeginnerResponse> {
    
}
