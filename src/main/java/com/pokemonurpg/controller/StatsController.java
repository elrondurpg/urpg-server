package com.pokemonurpg.controller;

import com.pokemonurpg.RestResponse;
import com.pokemonurpg.dto.security.Authenticated;
import com.pokemonurpg.dto.stats.input.StatsInputDto;
import com.pokemonurpg.dto.stats.response.OwnedPokemonDto;
import com.pokemonurpg.dto.stats.response.StatsDto;
import com.pokemonurpg.service.trainer.MemberService;
import com.pokemonurpg.service.trainer.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
@CrossOrigin
public class StatsController {
    private StatsService statsService;
    private MemberService memberService;

    @Autowired
    public StatsController(StatsService statsService, MemberService memberService) {
        this.statsService = statsService;
        this.memberService = memberService;
    }

    @GetMapping(path="/{name}")
    public @ResponseBody
    RestResponse getStatsByName(@PathVariable("name") String name) {
        try {
            StatsDto dto = statsService.findByName(name);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }

    @GetMapping(path="/pokemon/{dbid}")
    public @ResponseBody
    RestResponse getOwnedPokemonByDbid(@PathVariable("dbid") int dbid) {
        try {
            OwnedPokemonDto dto = statsService.findOwnedPokemonByDbid(dbid);
            if (dto != null) {
                return new RestResponse(200, dto);
            }
            else return new RestResponse(404, null);
        } catch (IllegalStateException e) {
            return new RestResponse(400, null);
        }
    }

    @PutMapping(path="/{name}")
    public @ResponseBody
    RestResponse updateStats(@RequestBody Authenticated<StatsInputDto> input, @PathVariable("name") String name) {
        if (memberService.isCurrentUser(input.getSession(), name) || memberService.authenticateAndAuthorize(input.getSession(), "Write User")) {
            StatsInputDto stats = input.getPayload();
            /*try {
                logger.info("{} requested UPDATE SPECIES with input={}", input.getSession().getId(), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input.getPayload()));
            } catch (JsonProcessingException e) {
                logger.catching(e);
                return new RestResponse(500, "Internal server error. Please contact your system administrator.");
            }*/
            Errors errors = statsService.updateStats(stats, name);
            if (errors.hasErrors()) {
                return new RestResponse(400, errors.getAllErrors());
            } else return new RestResponse(200, "Trainer " + name + " was updated successfully!");
        } else return new RestResponse(401, "The current user is not logged in or does not have permissions to perform the requested action.");
    }
}
