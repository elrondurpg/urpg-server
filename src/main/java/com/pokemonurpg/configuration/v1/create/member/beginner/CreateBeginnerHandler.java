package com.pokemonurpg.configuration.v1.create.member.beginner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.create.member.common.CreateMemberHandler;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonHandler;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonRequest;
import com.pokemonurpg.configuration.v1.create.pokemon.starter.CreateStarterPokemonResponse;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.model.OwnedItem;
import com.pokemonurpg.configuration.v1.member.member.service.OwnedItemService;

@Service
public class CreateBeginnerHandler extends CreateMemberHandler<CreateBeginnerRequest, CreateBeginnerResponse>{

    @Resource
    private OwnedItemService ownedItemService;

    @Resource
    private CreateStarterPokemonHandler createStarterPokemonHandler;

    @Override
    protected Member createMember(CreateBeginnerRequest dto) {
        Member member = super.createMember(dto);
        member.setJoinDate(new Date());
        member.setMoney(15000);
        return member;
    }

    @Override
    protected CreateBeginnerResponse createResponse(Member member, CreateBeginnerRequest request) {
        return CreateBeginnerResponse.builder()
            .accessToken(request.getAccessToken().getAccessToken())
            .name(member.getName())
            .discordId(member.getDiscordId())
            .roles(member.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
            .starter(createStarter(member, request.getStarter()))
            .items(createStartingItems(member))
            .build();
    }

    private List<OwnedItem> createStartingItems(Member member) {
        List<OwnedItem> items = new ArrayList<>();
        items.add(ownedItemService.add(member, "Pokemon Mart Ticket", 3));
        items.add(ownedItemService.add(member, "EM Mart Ticket", 3));
        items.add(ownedItemService.add(member, "HM Mart Ticket", 1));
        items.add(ownedItemService.add(member, "Starter Ribbon Coupon", 1));
        items.add(ownedItemService.add(member, "Rare Candy", 5));
        return items;
    }

    private CreateStarterPokemonResponse createStarter(Member member, CreateStarterPokemonRequest starter) {
        return createStarterPokemonHandler.handle(member, starter);
    }
}
