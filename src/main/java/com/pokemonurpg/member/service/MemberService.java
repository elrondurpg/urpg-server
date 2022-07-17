package com.pokemonurpg.member.service;

import com.pokemonurpg.general.service.ObtainedService;
import com.pokemonurpg.gym.service.KnownChampionService;
import com.pokemonurpg.gym.service.KnownEliteFourMemberService;
import com.pokemonurpg.gym.service.KnownGymLeaderService;
import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.security.dto.RegistrationInputDto;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.HashService;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.input.MemberRoleInputDto;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.input.MemberInputDto;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.repository.MemberRepository;
import com.pokemonurpg.core.service.NamedObjectService;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.service.SpeciesService;
import com.pokemonurpg.stats.input.*;
import com.pokemonurpg.stats.models.OwnedPokemon;
import com.pokemonurpg.stats.repository.OwnedPokemonRepository;
import com.pokemonurpg.stats.service.*;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pokemonurpg.strings.PermissionNames.WRITE_MEMBER_PERMISSION;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

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
    private LegendaryProgressService legendaryProgressService;

    @Resource
    private OwnedItemService ownedItemService;

    @Resource
    private AesEncryptionService aesEncryptionService;

    @Resource
    private EliteFourVictoryService eliteFourVictoryService;

    @Resource
    private ChampionVictoryService championVictoryService;

    @Resource
    private GymVictoryService gymVictoryService;

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Resource
    private KnownChampionService knownChampionService;

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private KnownNameClaimService knownNameClaimService;

    @Resource
    private SpeciesService speciesService;

    @Resource
    private ObtainedService obtainedService;

    @Resource
    private OwnedPokemonRepository ownedPokemonRepository;

    public Member findByDbid(Integer dbid) {
        return memberRepository.findByDbid(dbid);
    }

    public List<String> findNamesBy(String username, Boolean bot) {
        Member example = new Member();
        example.setName(username);
        example.setBot(bot);

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
            .withMatcher("username", startsWith());

        List<Member> members = memberRepository.findAll(Example.of(example, matcher));
        return members.stream().map(Member::getName).collect(Collectors.toList());
    }

    public Member findByNameExact(String name) {
        return memberRepository.findByName(name);
    }

    public Member findByName(String name) {
        Member member = findByNameExact(name);
        if (member == null && name != null) {
            return memberRepository.findFirstByNameStartingWith(name);
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

    public Member registerNew(RegistrationInputDto input, String discordId, OAuthAccessTokenResponse accessTokenResponse) {
        Member member = new Member();
        member.setName(input.getName());
        member.setDiscordId(discordId);
        member.setBot(false);
        member.initSalt();

        Set<Role> currentRoles = member.getRoles();
        Role memberRole = roleService.findByName("Member");
        currentRoles.add(memberRole);

        member.setJoinDate(new Date());

        member.setMoney(15000);

        memberRepository.save(member);

        ownedItemService.add(member, "Pokemon Mart Ticket", 3);
        ownedItemService.add(member, "EM Mart Ticket", 3);
        ownedItemService.add(member, "HM Mart Ticket", 1);
        ownedItemService.add(member, "Starter Ribbon Coupon", 1);
        ownedItemService.add(member, "Rare Candy", 5);

        Species species = speciesService.findByName(input.getStarter().getSpecies());
        OwnedPokemon pokemon = new OwnedPokemon(member, species, input.getStarter().getGender());
        pokemon.setObtained(obtainedService.findByName("Starter"));
        ownedPokemonRepository.save(pokemon);

        update(member, accessTokenResponse);
        return member;
    }

    public Member registerVet(RegistrationInputDto input, String discordId, OAuthAccessTokenResponse accessTokenResponse) {
        Member member = new Member();
        member.setName(input.getName());
        member.setDiscordId(discordId);
        member.setBot(false);
        member.initSalt();

        Set<Role> currentRoles = member.getRoles();
        Role memberRole = roleService.findByName("Member");
        currentRoles.add(memberRole);

        memberRepository.save(member);

        update(member, accessTokenResponse);
        return member;
    }

    public void registerForKnownName(String discordId) {
        KnownNameClaim claim = knownNameClaimService.findByDiscordId(discordId);
        if (claim != null) {
            Member member = new Member();
            member.setName(claim.getName());
            member.setDiscordId(discordId);
            member.setBot(false);
            member.initSalt();

            Set<Role> currentRoles = member.getRoles();
            Role memberRole = roleService.findByName("Member");
            currentRoles.add(memberRole);

            memberRepository.save(member);
        }
    }

    public void update(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        int salt = member.getSalt();

        member.setAccessToken(hashService.hash(accessTokenResponse.getAccessToken() + salt));

        if (accessTokenResponse.getRefreshToken() != null) {
            IvParameterSpec iv = aesEncryptionService.createIvParameterSpec();
            SecretKey key = aesEncryptionService.getKeyFromAccessToken(accessTokenResponse.getAccessToken(), salt);
            String encryptedRefreshToken = aesEncryptionService.encrypt(accessTokenResponse.getRefreshToken(), key, iv);
            member.setRefreshToken(encryptedRefreshToken);
            member.setRefreshTokenIv(iv.getIV());
        }

        member.setSessionExpire(Long.parseLong(accessTokenResponse.getExpiresIn()) + (systemService.currentTimeMillis() / 1000));

        memberRepository.save(member);
    }

    public Member update(MemberInputDto input, int dbid) {
        Member member = memberRepository.findByDbid(dbid);
        if (member != null) {
            preupdateAssociatedValues(input, member);
            member.update(input);
            updateEmbeddedValues(input, member);
            memberRepository.save(member);
            updateAssociatedValues(input, member);
        }
        return member;
    }

    private void preupdateAssociatedValues(MemberInputDto input, Member member) {
        knownGymLeaderService.rename(input, member);
        knownEliteFourMemberService.rename(input, member);
        knownChampionService.rename(input, member);
    }

    private void updateEmbeddedValues(MemberInputDto input, Member member) {
        updateRoles(input, member);
    }

    private void updateRoles(MemberInputDto input, Member member) {
        if (authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)) {
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
    }

    private void updateAssociatedValues(MemberInputDto input, Member member) {
        updateLegendaryProgress(input, member);
        updateOwnedItems(input, member);
        updateEliteFourVictories(input, member);
        updateChampionVictories(input, member);
        updateGymVictories(input, member);
    }

    private void updateLegendaryProgress(MemberInputDto input, Member member) {
        List<LegendaryProgressInputDto> legendaryProgresses = input.getLegendaryProgress();
        for (LegendaryProgressInputDto legendaryProgress : legendaryProgresses) {
            legendaryProgressService.update(legendaryProgress, member);
        }
        member.setLegendaryProgress(legendaryProgressService.findByTrainer(member));
    }

    private void updateOwnedItems(MemberInputDto input, Member member) {
        List<OwnedItemInputDto> items = input.getItems();
        for (OwnedItemInputDto item : items) {
            ownedItemService.update(member, item);
        }
    }

    private void updateEliteFourVictories(MemberInputDto input, Member member) {
        List<EliteFourVictoryInputDto> victories = input.getEliteFourVictories();
        for(EliteFourVictoryInputDto victory : victories) {
            eliteFourVictoryService.update(victory, member, knownEliteFourMemberService.findByName(victory.getDefender()));
        }
    }

    private void updateChampionVictories(MemberInputDto input, Member member) {
        List<ChampionVictoryInputDto> victories = input.getChampionVictories();
        for(ChampionVictoryInputDto victory : victories) {
            championVictoryService.update(victory, member, knownChampionService.findByName(victory.getDefender()));
        }
    }

    private void updateGymVictories(MemberInputDto input, Member member) {
        List<GymVictoryInputDto> victories = input.getGymVictories();
        for(GymVictoryInputDto victory : victories) {
            gymVictoryService.update(victory, member, knownGymLeaderService.findByName(victory.getDefender()));
        }
    }

    public void logout(Member member) {
        member.setRefreshToken(null);
        member.setRefreshTokenIv(null);
        member.setAccessToken(null);
        memberRepository.save(member);
    }

    public void delete(int dbid) {
        // TODO delete dependencies properly
        memberRepository.deleteByDbid(dbid);
    }
}
