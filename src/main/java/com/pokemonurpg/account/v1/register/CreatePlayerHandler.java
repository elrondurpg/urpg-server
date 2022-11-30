package com.pokemonurpg.account.v1.register;

import javax.annotation.Resource;

import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;
import com.pokemonurpg.member.service.RoleService;
import static com.pokemonurpg.account.v1.register.RegistrationConstants.*;

public abstract class CreatePlayerHandler<Dto extends CreatePlayerDto<? extends RegisterPlayerDto>> {

    @Resource
    protected MemberRepository memberRepository;

    @Resource
    protected RoleService roleService;

    @Resource
    protected UpdateMemberAccessTokensHandler updateMemberAccessTokensHandler;
    
    public Member handle(Dto dto) {
        handleDiscordIdDuplication(dto.getDiscordId());
        handleUsernameDuplication(dto);
        Member member = create(dto);
        memberRepository.save(member);
        doPostSaveActions(member, dto);
        return member;
    }

    protected void handleDiscordIdDuplication(String discordId) {
        if (!isDiscordIdUnique(discordId)) {
            throw new IllegalStateException(NON_UNIQUE_ID_ERROR);
        }
    }

    protected boolean isDiscordIdUnique(String discordId) {
        return memberRepository.findByDiscordId(discordId) == null;
    }

    protected void handleUsernameDuplication(Dto dto) {
        if (!isUsernameUnique(dto.getInput().getName())) {
            throw new IllegalStateException(NON_UNIQUE_USERNAME_ERROR);
        }
    }

    protected boolean isUsernameUnique(String name) {
        return memberRepository.findByName(name) == null;
    }

    protected Member create(Dto dto) {
        Member member = new Member();
        member.setName(dto.getInput().getName());
        member.setDiscordId(dto.getDiscordId());
        member.setBot(false);
        member.setDefaultValues();
        member.addRole(roleService.findByName("Member"));
        updateMemberAccessTokensHandler.updateLoginInformation(member, dto.getAccessToken());
        return member;
    }

    protected void doPostSaveActions(Member member, Dto dto) {

    }
}
