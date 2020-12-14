package com.pokemonurpg.member.service;

import com.pokemonurpg.core.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.core.security.service.HashService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.input.MemberRoleInputDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.repository.MemberRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.stats.input.EarnedBadgeInputDto;
import com.pokemonurpg.stats.input.LegendaryProgressInputDto;
import com.pokemonurpg.stats.input.OwnedItemInputDto;
import com.pokemonurpg.stats.service.EarnedBadgeService;
import com.pokemonurpg.stats.service.LegendaryProgressService;
import com.pokemonurpg.stats.service.OwnedItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class MemberService implements NamedObjectService<Member> {

    @Resource
    private MemberRepository memberRepository;

    @Resource
    private RoleService roleService;

    @Resource
    private HashService hashService;

    @Resource
    private SystemService systemService;

    @Resource
    private EarnedBadgeService earnedBadgeService;

    @Resource
    private LegendaryProgressService legendaryProgressService;

    @Resource
    private OwnedItemService ownedItemService;

    public List<String> findAllNames() {
        return memberRepository.findAllNames();
    }

    public Member findByDbid(int dbid) {
        return memberRepository.findByDbid(dbid);
    }

    public Member findByName(String name) {
        return memberRepository.findByUsername(name);
    }

    public Member findByUsername(String username) {
        Member member = memberRepository.findByUsername(username);
        if (member == null && username != null) {
            return memberRepository.findFirstByUsernameStartingWith(username);
        }
        else return member;
    }

    public Member findByDiscordId(String discordId) {
        return memberRepository.findByDiscordId(discordId);
    }

    public Member create(MemberInputDto input) {
        Member member = new Member(input);
        updateEmbeddedValues(input, member);
        memberRepository.save(member);
        updateAssociatedValues(input, member);
        return member;
    }

    public void update(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        int salt = member.getSalt();

        member.setAccessToken(hashService.hash(accessTokenResponse.getAccessToken() + salt));
        member.setRefreshToken(hashService.hash(accessTokenResponse.getRefreshToken() + salt));
        member.setSessionExpire(Long.parseLong(accessTokenResponse.getExpiresIn()) + (systemService.currentTimeMillis() / 1000));

        memberRepository.save(member);
    }

    public Member update(MemberInputDto input, int dbid) {
        Member member = memberRepository.findByDbid(dbid);
        if (member != null) {
            member.update(input);
            updateEmbeddedValues(input, member);
            memberRepository.save(member);
            updateAssociatedValues(input, member);
        }
        return member;
    }

    private void updateEmbeddedValues(MemberInputDto input, Member member) {
        updateRoles(input, member);
    }

    private void updateRoles(MemberInputDto input, Member member) {
        Set<Role> currentRoles = member.getRoles();

        for (MemberRoleInputDto role : input.getRoles()) {
            String name = role.getName();
            Role roleObject = roleService.findByName(name);
            if (role.getDelete())
                currentRoles.remove(roleObject);
            else
                currentRoles.add(roleObject);
        }
    }

    private void updateAssociatedValues(MemberInputDto input, Member member) {
        updateEarnedBadges(input, member);
        updateLegendaryProgress(input, member);
        updateOwnedItems(input, member);
    }

    private void updateEarnedBadges(MemberInputDto input, Member member) {
        List<EarnedBadgeInputDto> badges = input.getBadges();
        for(EarnedBadgeInputDto badge : badges) {
            earnedBadgeService.update(member, badge);
        }
    }

    private void updateLegendaryProgress(MemberInputDto input, Member member) {
        List<LegendaryProgressInputDto> legendaryProgresses = input.getLegendaryProgress();
        for (LegendaryProgressInputDto legendaryProgress : legendaryProgresses) {
            legendaryProgressService.update(legendaryProgress, member);
        }
    }

    private void updateOwnedItems(MemberInputDto input, Member member) {
        List<OwnedItemInputDto> items = input.getItems();
        for (OwnedItemInputDto item : items) {
            ownedItemService.update(member, item);
        }
    }
}