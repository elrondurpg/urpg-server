package com.pokemonurpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class PermissionController {
    private PermissionService permissionService;
    private MemberService memberService;
    private Logger logger = LogManager.getLogger(PermissionController.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public PermissionController(PermissionService permissionService, MemberService memberService) {
        this.permissionService = permissionService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllPermissions() {
        return new RestResponse(200, permissionService.findAll());
    }

    @PostMapping
    public @ResponseBody
    RestResponse createPermission(@RequestBody Authenticated<String> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Permission")) {
            String permission = input.getPayload();
            try {
                logger.info("{} requested CREATE PERMISSION with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = permissionService.createPermission(permission);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200, "Permission " + permission + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }
}
