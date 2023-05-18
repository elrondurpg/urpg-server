package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.configuration.v1.abilities.AbilityService;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeService;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeService;
import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectService;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import com.pokemonurpg.configuration.v1.natures.NatureService;
import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodService;
import com.pokemonurpg.configuration.v1.sections.SectionService;
import com.pokemonurpg.entities.v1.*;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.configuration.v1.sitemenuitems.MenuItemService;
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.configuration.v1.permissions.PermissionService;
import com.pokemonurpg.configuration.v1.roles.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NamedObjectServiceFactoryTest {

    @InjectMocks
    private NamedObjectServiceFactory namedObjectServiceFactory;

    @Mock
    private AbilityService abilityService;

    @Mock
    private ArtRankService artRankService;

    @Mock
    private AttackCategoryService attackCategoryService;

    @Mock
    private AttackService attackService;

    @Mock
    private AttackTargetTypeService attackTargetTypeService;

    @Mock
    private BadgeService badgeService;

    @Mock
    private ChampionSlotService championSlotService;

    @Mock
    private ContestAttributeService contestAttributeService;

    @Mock
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Mock
    private GymService gymService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private ChampionService championService;

    @Mock
    private EliteFourMemberService eliteFourMemberService;

    @Mock
    private GymLeaderService gymLeaderService;

    @Mock
    private MemberService memberService;

    @Mock
    private MenuItemService menuItemService;

    @Mock
    private NatureService natureService;

    @Mock
    private CaptureMethodService captureMethodService;

    @Mock
    private ContestEffectService contestEffectService;

    @Mock
    private ParkLocationService parkLocationService;

    @Mock
    private ParkRankService parkRankService;

    @Mock
    private PermissionService permissionService;

    @Mock
    private RoleService roleService;

    @Mock
    private SectionService sectionService;

    @Mock
    private PokemonService pokemonService;

    @Mock
    private StoryRankService storyRankService;

    @Mock
    private TypeService typeService;

    @Mock
    private ImageFolderService imageFolderService;

    @Test
    public void returnsAbilityService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Ability.class);
        assertEquals(abilityService, service);
    }

    @Test
    public void returnsArtRankService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ArtRank.class);
        assertEquals(artRankService, service);
    }

    @Test
    public void returnsAttackService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Attack.class);
        assertEquals(attackService, service);
    }

    @Test
    public void returnsAttackCategoryService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(AttackCategory.class);
        assertEquals(attackCategoryService, service);
    }

    @Test
    public void returnsAttackTargetTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(AttackTargetType.class);
        assertEquals(attackTargetTypeService, service);
    }

    @Test
    public void returnsBadgeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Badge.class);
        assertEquals(badgeService, service);
    }

    @Test
    public void returnsChampionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ChampionSlot.class);
        assertEquals(championSlotService, service);
    }

    @Test
    public void returnsContestAttributeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ContestAttribute.class);
        assertEquals(contestAttributeService, service);
    }

    @Test
    public void returnsEliteFourService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(EliteFourMemberSlot.class);
        assertEquals(eliteFourMemberSlotService, service);
    }

    @Test
    public void returnsGymService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Gym.class);
        assertEquals(gymService, service);
    }

    @Test
    public void returnsGymLeagueService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(GymLeague.class);
        assertEquals(gymLeagueService, service);
    }

    @Test
    public void returnsImageFolderService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ImageFolder.class);
        assertEquals(imageFolderService, service);
    }

    @Test
    public void returnsItemService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Item.class);
        assertEquals(itemService, service);
    }

    @Test
    public void returnsKnownChampionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Champion.class);
        assertEquals(championSlotService, service);
    }

    @Test
    public void returnsKnownEliteFourMemberService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(EliteFourMember.class);
        assertEquals(eliteFourMemberService, service);
    }

    @Test
    public void returnsKnownGymLeaderService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(GymLeader.class);
        assertEquals(gymLeaderService, service);
    }

    @Test
    public void returnsMemberService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Member.class);
        assertEquals(memberService, service);
    }

    @Test
    public void returnsMenuItemService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(MenuItem.class);
        assertEquals(menuItemService, service);
    }

    @Test
    public void returnsNatureService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Nature.class);
        assertEquals(natureService, service);
    }

    @Test
    public void returnsObtainedService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(CaptureMethod.class);
        assertEquals(captureMethodService, service);
    }

    @Test
    public void returnsOrasContestMoveTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ContestEffect.class);
        assertEquals(contestEffectService, service);
    }

    @Test
    public void returnsParkLocationService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ParkLocation.class);
        assertEquals(parkLocationService, service);
    }

    @Test
    public void returnsParkRankService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ParkRank.class);
        assertEquals(parkRankService, service);
    }

    @Test
    public void returnsPermissionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Permission.class);
        assertEquals(permissionService, service);
    }

    @Test
    public void returnsRoleService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Role.class);
        assertEquals(roleService, service);
    }

    @Test
    public void returnsSectionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Section.class);
        assertEquals(sectionService, service);
    }

    @Test
    public void returnsSpeciesService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Pokemon.class);
        assertEquals(pokemonService, service);
    }

    @Test
    public void returnsStoryRankService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(StoryRank.class);
        assertEquals(storyRankService, service);
    }

    @Test
    public void returnsTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Type.class);
        assertEquals(typeService, service);
    }

    @Test
    public void returnsNull() {
        assertNull(namedObjectServiceFactory.getServiceForClass(null));
    }

    @Test
    public void returnsNullForInvalidType() {
        assertNull(namedObjectServiceFactory.getServiceForClass(String.class));
    }

}