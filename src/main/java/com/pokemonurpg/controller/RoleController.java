package com.pokemonurpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.RoleDto;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.security.RoleInputDto;
import com.pokemonurpg.object.Member;
import com.pokemonurpg.service.MemberService;
import com.pokemonurpg.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    private RoleService roleService;
    private MemberService memberService;
    private Logger logger = LogManager.getLogger(RoleController.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RoleController(RoleService roleService, MemberService memberService) {
        this.roleService = roleService;
        this.memberService = memberService;
    }

    @GetMapping
    public @ResponseBody
    RestResponse getAllRoles() {
        return new RestResponse(200, roleService.findAll());
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getRoleByName(@PathVariable("name") String name) {
        try {
            RoleDto dto = roleService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, "Role not found.");
        } catch (IllegalStateException e) {
            return new RestResponse(400, "Bad request.");
        }
    }

    @PostMapping
    public @ResponseBody
    RestResponse createRole(@RequestBody Authenticated<RoleInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Role")) {
            RoleInputDto role = input.getPayload();
            try {
                logger.info("{} requested CREATE ROLE with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = roleService.createRole(role);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200, "Role " + role.getName() + " was created successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }

    @PutMapping
    public @ResponseBody
    RestResponse updateRole(@RequestBody Authenticated<RoleInputDto> input) {
        if (memberService.authenticateAndAuthorize(input.getSession(), "Write Role")) {
            RoleInputDto role = input.getPayload();
            try {
                logger.info("{} requested UPDATE ROLE with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }
            Errors errors = roleService.updateRole(role);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            }
            else return new RestResponse(200, "Role " + role.getName() + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }
}
