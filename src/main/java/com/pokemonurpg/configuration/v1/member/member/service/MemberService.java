package com.pokemonurpg.configuration.v1.member.member.service;

import static com.pokemonurpg.strings.PermissionNames.WRITE_MEMBER_PERMISSION;

import com.pokemonurpg.configuration.v1.lib.service.NamedConfigurationService;
import com.pokemonurpg.configuration.v1.member.member.input.MemberInputDto;
import com.pokemonurpg.configuration.v1.member.member.model.Member;
import com.pokemonurpg.configuration.v1.member.member.repository.MemberRepository;


import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.gym.knownchampion.service.KnownChampionService;
import com.pokemonurpg.configuration.v1.gym.knownelitefourmember.service.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.gym.knowngymleader.service.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.member.member.input.ChampionVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.input.EliteFourVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.input.GymVictoryInputDto;
import com.pokemonurpg.configuration.v1.member.member.input.LegendaryProgressInputDto;
import com.pokemonurpg.configuration.v1.member.member.input.MemberRoleInputDto;
import com.pokemonurpg.configuration.v1.member.member.input.OwnedItemInputDto;
import com.pokemonurpg.core.service.SystemService;
import com.pokemonurpg.member.models.KnownNameClaim;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.service.KnownNameClaimService;
import com.pokemonurpg.member.service.RoleService;
import com.pokemonurpg.security.models.OAuthAccessTokenResponse;
import com.pokemonurpg.security.service.AesEncryptionService;
import com.pokemonurpg.security.service.AuthorizationService;
import com.pokemonurpg.security.service.HashService;

@Service
public class MemberService extends NamedConfigurationService<Member, MemberInputDto> {
    private MemberRepository repository;
    private RoleService roleService;
    private HashService hashService;
    private SystemService systemService;
    private LegendaryProgressService legendaryProgressService;
    private OwnedItemService ownedItemService;
    private AesEncryptionService aesEncryptionService;
    private EliteFourVictoryService eliteFourVictoryService;
    private ChampionVictoryService championVictoryService;
    private GymVictoryService gymVictoryService;
    private KnownGymLeaderService knownGymLeaderService;
    private KnownEliteFourMemberService knownEliteFourMemberService;
    private KnownChampionService knownChampionService;
    private AuthorizationService authorizationService;
    private KnownNameClaimService knownNameClaimService;

    @Autowired
    

    public Member findByDiscordId(String discordId) {
        return repository.findByDiscordId(discordId);
    }

    public MemberService(MemberRepository repository, Class<Member> modelClass,RoleService roleService, HashService hashService, SystemService systemService,
            LegendaryProgressService legendaryProgressService, OwnedItemService ownedItemService,
            AesEncryptionService aesEncryptionService, EliteFourVictoryService eliteFourVictoryService,
            ChampionVictoryService championVictoryService, GymVictoryService gymVictoryService,
            KnownGymLeaderService knownGymLeaderService, KnownEliteFourMemberService knownEliteFourMemberService,
            KnownChampionService knownChampionService, AuthorizationService authorizationService,
            KnownNameClaimService knownNameClaimService) {
        super(repository, modelClass);
        this.repository = repository;
        this.roleService = roleService;
        this.hashService = hashService;
        this.systemService = systemService;
        this.legendaryProgressService = legendaryProgressService;
        this.ownedItemService = ownedItemService;
        this.aesEncryptionService = aesEncryptionService;
        this.eliteFourVictoryService = eliteFourVictoryService;
        this.championVictoryService = championVictoryService;
        this.gymVictoryService = gymVictoryService;
        this.knownGymLeaderService = knownGymLeaderService;
        this.knownEliteFourMemberService = knownEliteFourMemberService;
        this.knownChampionService = knownChampionService;
        this.authorizationService = authorizationService;
        this.knownNameClaimService = knownNameClaimService;
    }

    @Override
    public void setKeyValues(Member model, MemberInputDto input) {
        model.setDiscordId(input.getDiscordId());
        model.setBot(input.getBot());
    }

    public void registerForKnownName(String discordId) {
        KnownNameClaim claim = knownNameClaimService.findByDiscordId(discordId);
        if (claim != null) {
            Member member = new Member();
            member.setName(claim.getName());
            member.setDiscordId(discordId);
            member.setBot(false);
            member.setDefaultValues();
            member.addRole(roleService.findByName("Member"));

            repository.save(member);
        }
    }

    public void update(Member member, OAuthAccessTokenResponse accessTokenResponse) {
        int salt = member.getSalt();

        member.setAccessToken(hashService.hash(accessTokenResponse.getAccessToken() + salt));

        if (accessTokenResponse.getRefreshToken() != null) {
            javax.crypto.spec.IvParameterSpec iv = aesEncryptionService.createIvParameterSpec();
            SecretKey key = aesEncryptionService.getKeyFromAccessToken(accessTokenResponse.getAccessToken(), salt);
            String encryptedRefreshToken = aesEncryptionService.encrypt(accessTokenResponse.getRefreshToken(), key, iv);
            member.setRefreshToken(encryptedRefreshToken);
            member.setRefreshTokenIv(iv.getIV());
        }

        member.setSessionExpire(Long.parseLong(accessTokenResponse.getExpiresIn()) + (systemService.currentTimeMillis() / 1000));

        repository.save(member);
    }

    @Override
    protected void preupdateAssociatedValues(Member member, MemberInputDto input) {
        knownGymLeaderService.renameForMember(member, input.getName());
        knownEliteFourMemberService.renameForMember(member, input.getName());
        knownChampionService.renameForMember(member, input.getName());
    }

    @Override
    protected void updateBase(Member member, MemberInputDto input) {
        super.updateBase(member, input);
        setIfNotNull(input.getWins(), member::setWins);
        setIfNotNull(input.getLosses(), member::setLosses);
        setIfNotNull(input.getDraws(), member::setDraws);   
        setIfNotNull(input.getJoinDate(), member::setJoinDate);
        setIfNotNull(input.getMoney(), member::setMoney);
    }

    @Override
    protected void updateEmbeddedValues(Member member, MemberInputDto input) {
        updateRoles(member, input);
    }

    private void updateRoles(Member member, MemberInputDto input) {
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

    @Override
    protected void updateAssociatedValues(Member model, MemberInputDto input) {
        updateLegendaryProgress(input, model);
        updateOwnedItems(input, model);
        updateEliteFourVictories(input, model);
        updateChampionVictories(input, model);
        updateGymVictories(input, model);
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

    @Override
    protected void deleteAssociatedValues(Member model) {
        // TODO Auto-generated method stub
        
    }

    public void logout(Member member) {
        member.setRefreshToken(null);
        member.setRefreshTokenIv(null);
        member.setAccessToken(null);
        repository.save(member);
    }

}
