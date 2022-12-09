package com.pokemonurpg.account.v1.register.beginner.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.account.v1.register.common.internal.CreateMemberHandler;
import com.pokemonurpg.account.v1.register.common.internal.UpdateMemberAccessTokensHandler;
import com.pokemonurpg.configuration.v1.member.member.service.OwnedItemService;
import com.pokemonurpg.entities.v1.member.KnownNameClaimRepository;
import com.pokemonurpg.entities.v1.member.Member;
import com.pokemonurpg.entities.v1.member.MemberRepository;
import com.pokemonurpg.entities.v1.member.OwnedItem;
import com.pokemonurpg.entities.v1.member.RoleRepository;

@Service
public class CreateBeginnerHandler extends CreateMemberHandler<CreateBeginnerRequest, CreateBeginnerResponse>{

    private OwnedItemService ownedItemService;
    private CreateStarterPokemonHandler createStarterPokemonHandler;

    @Autowired
    public CreateBeginnerHandler(MemberRepository memberRepository, RoleRepository roleRepository,
            UpdateMemberAccessTokensHandler updateMemberAccessTokensHandler,
            KnownNameClaimRepository knownNameClaimRepository, OwnedItemService ownedItemService,
            CreateStarterPokemonHandler createStarterPokemonHandler) {
        super(memberRepository, roleRepository, updateMemberAccessTokensHandler, knownNameClaimRepository);
        this.ownedItemService = ownedItemService;
        this.createStarterPokemonHandler = createStarterPokemonHandler;
    }

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
