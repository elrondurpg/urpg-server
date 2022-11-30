package com.pokemonurpg.account.v1.register.veteran;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonurpg.account.v1.register.common.RegisterMemberController;
import com.pokemonurpg.account.v1.register.common.RegisterMemberHandler;
import com.pokemonurpg.account.v1.register.common.RegisterMemberRequest;
import com.pokemonurpg.account.v1.register.common.RegisterMemberResponse;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberRequest;
import com.pokemonurpg.account.v1.register.common.internal.CreateMemberResponse;

@RestController
@RequestMapping("/registration/v1/veteran")
@CrossOrigin
@Validated
public class RegisterVeteranController extends RegisterMemberController<RegisterMemberRequest, RegisterMemberResponse> {

    public RegisterVeteranController(
        RegisterMemberHandler
            <RegisterMemberRequest, 
            RegisterMemberResponse, 
            CreateMemberRequest, 
            CreateMemberResponse> 
        handler
    ) {
        super(handler);
    }
    
}
