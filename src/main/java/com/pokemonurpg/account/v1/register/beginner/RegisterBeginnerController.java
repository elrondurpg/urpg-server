package com.pokemonurpg.account.v1.register.beginner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.beginner.internal.CreateBeginnerRequest;
import com.pokemonurpg.account.v1.register.beginner.internal.CreateBeginnerResponse;
import com.pokemonurpg.account.v1.register.common.RegisterMemberController;
import com.pokemonurpg.account.v1.register.common.RegisterMemberHandler;

@RestController
@RequestMapping("/registration/v1/beginner")
@CrossOrigin
@Validated
public class RegisterBeginnerController extends RegisterMemberController<RegisterBeginnerRequest, RegisterBeginnerResponse> {

    @Autowired
    public RegisterBeginnerController(
        RegisterMemberHandler
            <RegisterBeginnerRequest, 
            RegisterBeginnerResponse, 
            CreateBeginnerRequest, 
            CreateBeginnerResponse> 
        handler
    ) {
        super(handler);
    }
    
}
