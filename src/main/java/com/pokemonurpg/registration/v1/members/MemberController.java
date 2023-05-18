package com.pokemonurpg.registration.v1.members;

import com.pokemonurpg.lib.v1.annotations.AllowAll;
import com.pokemonurpg.lib.v1.validationgroups.ObjectCreation;
import com.pokemonurpg.lib.v1.validationgroups.RegisterNewUser;
import com.pokemonurpg.login.v1.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import static com.pokemonurpg.lib.v1.strings.ErrorStrings.ERROR_ON_CLAIM;
import static com.pokemonurpg.lib.v1.strings.ErrorStrings.ERROR_ON_MISSING_REGISTRATION_TYPE;

@RestController
@RequestMapping("/urpg-registration/v1/members")
@CrossOrigin
@Validated
public class MemberController {

    @Resource
    private RegistrationService registrationService;

    @AllowAll
    @PutMapping
    public @ResponseBody
    ResponseEntity<?> claimKnownName(@Valid @RequestBody RegistrationRequest input) {
        try {
            registrationService.claimKnownName(input);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ERROR_ON_CLAIM);
        }
    }

    @AllowAll
    @Validated({ObjectCreation.class, RegisterNewUser.class})
    @PostMapping
    public @ResponseBody
    Session registerNew(@Valid @RequestBody RegistrationRequest input, @QueryParam(value = "type") String type) {
        if (type != null) {
            switch(type.toLowerCase()) {
                case "new": return registrationService.registerNew(input);
                case "existing": return registrationService.registerVet(input);
                default:
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERROR_ON_MISSING_REGISTRATION_TYPE);
    }
}
