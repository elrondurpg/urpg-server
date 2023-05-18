package com.pokemonurpg.member.service;

import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.configuration.v1.roles.RoleService;
import com.pokemonurpg.login.v1.OAuthAccessTokenResponse;
import com.pokemonurpg.login.v1.AesEncryptionService;
import com.pokemonurpg.login.v1.AuthorizationService;
import com.pokemonurpg.login.v1.HashService;
import com.pokemonurpg.lib.v1.service.SystemService;
import com.pokemonurpg.configuration.v1.members.MemberInputDto;
import com.pokemonurpg.entities.v1.Role;
import com.pokemonurpg.entities.v1.Member;
import com.pokemonurpg.infrastructure.v1.data.jpa.MemberRepository;
import com.pokemonurpg.stats.v1.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MemberServiceTest {
    private final static Boolean                    BOT = true;

    private final static byte[]                     IV = { 12, 32 };

    private final static ChampionVictoryInputDto CHAMPION_VICTORY = mock(ChampionVictoryInputDto.class);

    private final static EliteFourVictoryInputDto ELITE_FOUR_VICTORY = mock(EliteFourVictoryInputDto.class);

    private final static GymVictoryInputDto GYM_VICTORY = mock(GymVictoryInputDto.class);

    private final static Integer                    DBID = 32432;
    private final static Integer                    SALT = 34222;

    private final static IvParameterSpec            IV_PARAMETER_SPEC = new IvParameterSpec(IV);

    private final static Long                       EXPIRES_IN = 69L;
    private final static Long                       CURRENT_TIME_MILLIS = 432000L;

    private final static LegendaryProgressInputDto LEGENDARY_PROGRESS = mock(LegendaryProgressInputDto.class);

    private final static Member                     CURRENT_MEMBER = new Member();

    private final static OwnedItemInputDto OWNED_ITEM = mock(OwnedItemInputDto.class);

    private final static Role                       CURRENT_ROLE = mock(Role.class);
    private final static Role                       NEW_ROLE = mock(Role.class);

    private final static SecretKey                  SECRET_KEY = mock(SecretKey.class);

    private final static String                     ACCESS_TOKEN = "ACCESS_TOKEN";
    private final static String                     CURRENT_ROLE_NAME = "CURRENT_ROLE_NAME";
    private final static String                     DISCORD_ID = "DISCORD_ID";
    private final static String                     ENCRYPTED_REFRESH_TOKEN = "ENCRYPTED_REFRESH_TOKEN";
    private final static String                     HASHED_ACCESS_TOKEN = "43242324";
    private final static String                     NAME = "TEST";
    private final static String                     NEW_NAME = "NEW_NAME";
    private final static String                     NEW_ROLE_NAME = "NEW_ROLE_NAME";
    private final static String                     OLD_NAME = "OLD_NAME";
    private final static String                     REFRESH_TOKEN = "REFRESH_TOKEN";

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private HashService hashService;

    @Mock
    private SystemService systemService;

    @Mock
    private LegendaryProgressService legendaryProgressService;

    @Mock
    private OwnedItemService ownedItemService;

    @Mock
    private AesEncryptionService aesEncryptionService;

    private Member member = new Member();

    @Mock
    private RoleService roleService;

    @Mock
    private EliteFourVictoryService eliteFourVictoryService;

    @Mock
    private ChampionVictoryService championVictoryService;

    @Mock
    private GymVictoryService gymVictoryService;

    @Mock
    private KnownChampionService knownChampionService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

    @Mock
    private AuthorizationService authorizationService;

    @Captor
    ArgumentCaptor<String> knownBattleRecordNewName;

    @Captor
    ArgumentCaptor<String> knownBattleRecordOldName;

    @Test
    public void findNamesByReturnsValueFromRepository() {
        Member m = new Member();
        m.setName(NAME);

        when(memberRepository.findAll(any(Example.class))).thenReturn(Arrays.asList(m));

        List<String> names = memberService.findNamesBy(NAME, BOT);

        assertNotNull(names);
        assertEquals(1, names.size());
        assertEquals(NAME, names.get(0));
    }

    @Test
    public void findByDbid() {
        when(memberRepository.findByDbid(DBID)).thenReturn(member);
        assertEquals(member, memberService.findByDbid(DBID));
    }

    @Test
    public void findByDiscordId() {
        when(memberRepository.findByDiscordId(DISCORD_ID)).thenReturn(member);
        assertEquals(member, memberService.findByDiscordId(DISCORD_ID));
    }

    @Test
    public void findByName() {
        when(memberRepository.findByName(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByNameExact(NAME));
    }

    @Test
    public void findByUsernameExactMatch() {
        when(memberRepository.findByName(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByName(NAME));
    }

    @Test
    public void findByUsernameNotExactMatch() {
        when(memberRepository.findByName(NAME)).thenReturn(null);
        when(memberRepository.findFirstByNameStartingWith(NAME)).thenReturn(member);
        assertEquals(member, memberService.findByName(NAME));
    }
/*
    @Test
    public void create() throws NoSuchAlgorithmException {
        // Given a Role "newRole" known by name "NEW_ROLE_NAME"
        when(roleService.findByName(NEW_ROLE_NAME)).thenReturn(NEW_ROLE);

        // Given a MemberRoleInputDto with name = "NEW_ROLE_NAME"
        MemberRoleInputDto permInput1 = new MemberRoleInputDto();
        permInput1.setName(NEW_ROLE_NAME);

        // Given a MemberInputDto whose "roles" list includes permInput1
        MemberInputDto input = new MemberInputDto();
        input.setName(NAME);
        input.setRoles(Collections.singletonList(permInput1));
        input.setItems(Collections.singletonList(OWNED_ITEM));
        input.setLegendaryProgress(Collections.singletonList(LEGENDARY_PROGRESS));
        input.setEliteFourVictories(Collections.singletonList(ELITE_FOUR_VICTORY));
        input.setChampionVictories(Collections.singletonList(CHAMPION_VICTORY));
        input.setGymVictories(Collections.singletonList(GYM_VICTORY));

        // When I call memberService.create(input)
        Member member = memberService.create(input);
        assertEquals(NAME, member.getName());
        verify(memberRepository, times(1)).save(member);
        verify(ownedItemService, times(1)).update(member, OWNED_ITEM);
        verify(legendaryProgressService, times(1)).update(LEGENDARY_PROGRESS, member);
        verify(eliteFourVictoryService, times(1)).update(ELITE_FOUR_VICTORY, member);
        verify(championVictoryService, times(1)).update(CHAMPION_VICTORY, member);
        verify(gymVictoryService, times(1)).update(GYM_VICTORY, member);

        // Then that member's roles will contain NEW_ROLE
        assertTrue(member.getRoles().contains(NEW_ROLE));
    }*/
/*
    @Test
    public void updateExistingRecord() {
        // Given a Role "currentRole" known by name "CURRENT_ROLE_NAME"
        when(roleService.findByName(CURRENT_ROLE_NAME)).thenReturn(CURRENT_ROLE);

        // Given a Role "newRole" known by name "NEW_ROLE_NAME"
        when(roleService.findByName(NEW_ROLE_NAME)).thenReturn(NEW_ROLE);

        // Given a member that has role CURRENT_ROLE
        when(authorizationService.isAuthorized(WRITE_MEMBER_PERMISSION)).thenReturn(true);
        Set<Role> currentRoles = new HashSet<>();
        currentRoles.add(CURRENT_ROLE);
        CURRENT_MEMBER.setRoles(currentRoles);

        // Given a MemberRoleInputDto with name = "NEW_ROLE_NAME"
        MemberRoleInputDto permInput1 = new MemberRoleInputDto();
        permInput1.setName(NEW_ROLE_NAME);

        // Given a MemberRoleInputDto with name = "CURRENT_ROLE_NAME" and delete = true
        MemberRoleInputDto permInput2 = new MemberRoleInputDto();
        permInput2.setName(CURRENT_ROLE_NAME);
        permInput2.setDelete(true);

        // Given a MemberInputDto whose "roles" list includes permInput1 and permInput2
        MemberInputDto input = new MemberInputDto();
        input.setName(NEW_NAME);
        input.setRoles(Arrays.asList(permInput1, permInput2));

        when(memberRepository.findByDbid(DBID)).thenReturn(CURRENT_MEMBER);
        CURRENT_MEMBER.setName(OLD_NAME);

        // When I call memberService.updateAll(input, DBID)
        Member newMember = memberService.update(input, DBID);
        assertEquals(NEW_NAME, newMember.getName());
        verify(memberRepository, times(1)).save(newMember);

        verify(knownGymLeaderService, times(1)).update(knownBattleRecordNewName.capture(), knownBattleRecordOldName.capture());
        assertEquals(NEW_NAME, knownBattleRecordNewName.getValue());
        assertEquals(OLD_NAME, knownBattleRecordOldName.getValue());

        // Then currentRoles will contain NEW_ROLE and not CURRENT_ROLE
        assertTrue(currentRoles.contains(NEW_ROLE));
        assertFalse(currentRoles.contains(CURRENT_ROLE));
    }*/

    @Test
    public void updateNonExistingRecord() {
        MemberInputDto input = new MemberInputDto();
        input.setName(NAME);

        when(memberRepository.findByDbid(DBID)).thenReturn(null);

        Member member1 = memberService.update(input, DBID);
        assertNull(member1);
        verify(memberRepository, times(0)).save(any());
    }

    @Test
    public void updateWithAccessTokenResponse() {
        Member member = new Member();
        member.setSalt(SALT);

        OAuthAccessTokenResponse accessTokenResponse = new OAuthAccessTokenResponse();
        accessTokenResponse.setAccessToken(ACCESS_TOKEN);
        accessTokenResponse.setRefreshToken(REFRESH_TOKEN);
        accessTokenResponse.setExpiresIn("" + EXPIRES_IN);

        when(hashService.hash(ACCESS_TOKEN + SALT)).thenReturn(HASHED_ACCESS_TOKEN);

        when(aesEncryptionService.createIvParameterSpec()).thenReturn(IV_PARAMETER_SPEC);
        when(aesEncryptionService.getKeyFromAccessToken(ACCESS_TOKEN, SALT)).thenReturn(SECRET_KEY);
        when(aesEncryptionService.encrypt(REFRESH_TOKEN, SECRET_KEY, IV_PARAMETER_SPEC)).thenReturn(ENCRYPTED_REFRESH_TOKEN);

        when(systemService.currentTimeMillis()).thenReturn(CURRENT_TIME_MILLIS);

        memberService.update(member, accessTokenResponse);
        assertEquals(HASHED_ACCESS_TOKEN, member.getAccessToken());
        assertEquals(ENCRYPTED_REFRESH_TOKEN, member.getRefreshToken());
        assertArrayEquals(IV_PARAMETER_SPEC.getIV(), member.getRefreshTokenIv());

        long expires = EXPIRES_IN + CURRENT_TIME_MILLIS / 1000;
        assertEquals((Long) expires, member.getSessionExpire());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void logout() {
        Member member = new Member();
        member.setRefreshTokenIv(IV);
        member.setRefreshToken(REFRESH_TOKEN);
        member.setAccessToken(ACCESS_TOKEN);

        memberService.logout(member);
        assertNull(member.getRefreshToken());
        assertNull(member.getRefreshTokenIv());
        assertNull(member.getAccessToken());
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void delete() {
        memberService.delete(DBID);
        verify(memberRepository, times(1)).deleteByDbid(DBID);
    }
}