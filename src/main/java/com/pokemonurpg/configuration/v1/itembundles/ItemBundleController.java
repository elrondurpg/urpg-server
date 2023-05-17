package com.pokemonurpg.configuration.v1.itembundles;

import com.pokemonurpg.login.annotation.AllowAuthorized;
import com.pokemonurpg.lib.validation.ObjectCreation;
import com.pokemonurpg.entities.ItemBundle;
import com.pokemonurpg.login.annotation.AllowAll;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/itemBundle")
@CrossOrigin
@Validated
public class ItemBundleController {

    @Resource
    private ItemBundleService itemBundleService;

    @AllowAll
    @GetMapping
    public @ResponseBody
    List<String> findAllNames() {
        return itemBundleService.findAllNames();
    }

    @AllowAll
    @GetMapping(path="/{name}")
    public @ResponseBody
    ItemBundle findByName(@PathVariable("name") String name) {
        return itemBundleService.findByName(name);
    }

    @Validated(ObjectCreation.class)
    @AllowAuthorized(permission = "Write Item")
    @PostMapping
    public @ResponseBody
    ItemBundle create(@Valid @RequestBody ItemBundleInputDto input) {
        return itemBundleService.create(input);
    }

    @AllowAuthorized(permission = "Write Item")
    @PutMapping(path="/{dbid}")
    public @ResponseBody
    ItemBundle update(@Valid @RequestBody ItemBundleInputDto input, @PathVariable int dbid) {
        return itemBundleService.update(input, dbid);
    }
}
