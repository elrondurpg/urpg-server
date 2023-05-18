package com.pokemonurpg.configuration.v1.members;

import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.infrastructure.v1.data.jpa.MemberRepository;
import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodService;
import com.pokemonurpg.entities.v1.KnownNameClaim;
import com.pokemonurpg.registration.v1.members.KnownNameClaimService;
import com.pokemonurpg.configuration.v1.roles.RoleService;
import com.pokemonurpg.entities.v1.Pokemon;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import com.pokemonurpg.registration.v1.members.RegistrationRequest;
import com.pokemonurpg.login.v1.OAuthAccessTokenResponse;
import com.pokemonurpg.login.v1.AesEncryptionService;
import com.pokemonurpg.login.v1.AuthorizationService;
import com.pokemonurpg.login.v1.HashService;
import com.pokemonurpg.lib.v1.services.SystemService;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.lib.v1.services.NamedObjectService;
import com.pokemonurpg.entities.v1.OwnedPokemon;
import com.pokemonurpg.infrastructure.v1.data.jpa.OwnedPokemonRepository;
import com.pokemonurpg.stats.v1.*;
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

import static com.pokemonurpg.lib.v1.strings.PermissionNames.WRITE_MEMBER_PERMISSION;
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
    private GymLeaderService gymLeaderService;

    @Resource
    private EliteFourMemberService eliteFourMemberService;

    @Resource
    private ChampionService championService;

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private KnownNameClaimService knownNameClaimService;

    @Resource
    private PokemonService pokemonService;

    @Resource
    private CaptureMethodService captureMethodService;

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

    public Member create(MemberRequest input) {
        Member member = new Member(input);
        updateEmbeddedValues(input, member);
        memberRepository.save(member);
        updateAssociatedValues(input, member);
        return member;
    }

    public Member registerNew(RegistrationRequest input, String discordId, OAuthAccessTokenResponse accessTokenResponse) {
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

        Pokemon species = pokemonService.findByName(input.getStarter().getSpecies());
        OwnedPokemon pokemon = new OwnedPokemon(member, species, input.getStarter().getGender());
        pokemon.setObtained(captureMethodService.findByName("Starter"));
        ownedPokemonRepository.save(pokemon);

        update(member, accessTokenResponse);
        return member;
    }

    public Member registerVet(RegistrationRequest input, String discordId, OAuthAccessTokenResponse accessTokenResponse) {
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
            System.out.println("Updated member access token: " + accessTokenResponse.getAccessToken());
            SecretKey key = aesEncryptionService.getKeyFromAccessToken(accessTokenResponse.getAccessToken(), salt);
            String encryptedRefreshToken = aesEncryptionService.encrypt(accessTokenResponse.getRefreshToken(), key, iv);
            member.setRefreshToken(encryptedRefreshToken);
            member.setRefreshTokenIv(iv.getIV());
        }

        member.setSessionExpire(Long.parseLong(accessTokenResponse.getExpiresIn()) + (systemService.currentTimeMillis() / 1000));

        memberRepository.save(member);
    }

    public Member update(MemberRequest input, int dbid) {
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

    private void preupdateAssociatedValues(MemberRequest input, Member member) {
        gymLeaderService.rename(input, member);
        eliteFourMemberService.rename(input, member);
        championService.rename(input, member);
    }

    private void updateEmbeddedValues(MemberRequest input, Member member) {
        updateRoles(input, member);
    }

    private void updateRoles(MemberRequest input, Member member) {
        if (authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)) {
            Set<Role> currentRoles = member.getRoles();

            for (MemberRoleRequest role : input.getRoles()) {
                String name = role.getName();
                Role roleObject = roleService.findByName(name);
                if (role.getDelete())
                    currentRoles.remove(roleObject);
                else
                    currentRoles.add(roleObject);
            }
        }
    }

    private void updateAssociatedValues(MemberRequest input, Member member) {
        updateLegendaryProgress(input, member);
        updateOwnedItems(input, member);
        updateEliteFourVictories(input, member);
        updateChampionVictories(input, member);
        updateGymVictories(input, member);
    }

    private void updateLegendaryProgress(MemberRequest input, Member member) {
        List<LegendaryProgressRequest> legendaryProgresses = input.getLegendaryProgress();
        for (LegendaryProgressRequest legendaryProgress : legendaryProgresses) {
            legendaryProgressService.update(legendaryProgress, member);
        }
        member.setLegendaryProgress(legendaryProgressService.findByTrainer(member));
    }

    private void updateOwnedItems(MemberRequest input, Member member) {
        List<OwnedItemRequest> items = input.getItems();
        for (OwnedItemRequest item : items) {
            ownedItemService.update(member, item);
        }
    }

    private void updateEliteFourVictories(MemberRequest input, Member member) {
        List<EliteFourVictoryRequest> victories = input.getEliteFourVictories();
        for(EliteFourVictoryRequest victory : victories) {
            eliteFourVictoryService.update(victory, member, eliteFourMemberService.findByName(victory.getDefender()));
        }
    }

    private void updateChampionVictories(MemberRequest input, Member member) {
        List<ChampionVictoryRequest> victories = input.getChampionVictories();
        for(ChampionVictoryRequest victory : victories) {
            championVictoryService.update(victory, member, championService.findByName(victory.getDefender()));
        }
    }

    private void updateGymVictories(MemberRequest input, Member member) {
        List<GymVictoryRequest> victories = input.getGymVictories();
        for(GymVictoryRequest victory : victories) {
            gymVictoryService.update(victory, member, gymLeaderService.findByName(victory.getDefender()));
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
