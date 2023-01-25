package com.pokemonurpg.v2.infrastructure.configuration.v2;

import com.pokemonurpg.v2.domain.pokemon.type.*;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration/v2/type")
@CrossOrigin
@AllArgsConstructor
@Transactional
public class TypeController {
    private GetTypeInputBoundary getService;
    private CreateTypeInputBoundary createService;
    private UpdateTypeInputBoundary updateService;
    private DeleteTypeInputBoundary deleteService;

    @GetMapping
    public @ResponseBody
    GetTypeListResponse getList(GetTypeListRequest request) {
        return getService.getList(request);
    }

    @GetMapping("/{name}")
    public @ResponseBody
    GetTypeResponse getByName(@PathVariable(name = "name") String name) {
        return getService.getByNameStartingWith(name);
    }

    @PostMapping
    public @ResponseBody
    CreateTypeResponse create(@RequestBody CreateTypeRequest request) {
        return createService.create(request);
    }

    @PutMapping("/{dbid}")
    public @ResponseBody
    UpdateTypeResponse update(@PathVariable(name = "dbid") int dbid, @RequestBody UpdateTypeRequest request) {
        return updateService.update(dbid, request);
    }

    @DeleteMapping("/{dbid}")
    public @ResponseBody
    DeleteTypeResponse delete(@PathVariable(name = "dbid") int dbid) {
        return deleteService.deleteByDbid(dbid);
    }
}
