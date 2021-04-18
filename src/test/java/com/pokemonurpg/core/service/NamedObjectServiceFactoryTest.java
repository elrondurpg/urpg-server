package com.pokemonurpg.core.service;

import com.pokemonurpg.ability.models.Ability;
import com.pokemonurpg.ability.service.AbilityService;
import com.pokemonurpg.attack.models.Attack;
import com.pokemonurpg.attack.models.AttackCategory;
import com.pokemonurpg.attack.models.AttackTargetType;
import com.pokemonurpg.attack.service.AttackCategoryService;
import com.pokemonurpg.attack.service.AttackService;
import com.pokemonurpg.attack.service.AttackTargetTypeService;
import com.pokemonurpg.contest.models.ContestAttribute;
import com.pokemonurpg.contest.models.DPPContestMoveType;
import com.pokemonurpg.contest.models.ORASContestMoveType;
import com.pokemonurpg.contest.models.RSEContestMoveType;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.contest.service.DPPContestMoveTypeService;
import com.pokemonurpg.contest.service.ORASContestMoveTypeService;
import com.pokemonurpg.contest.service.RSEContestMoveTypeService;
import com.pokemonurpg.creative.models.ArtRank;
import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.general.models.Nature;
import com.pokemonurpg.general.models.Obtained;
import com.pokemonurpg.general.models.Section;
import com.pokemonurpg.general.service.NatureService;
import com.pokemonurpg.general.service.ObtainedService;
import com.pokemonurpg.general.service.SectionService;
import com.pokemonurpg.gym.models.Badge;
import com.pokemonurpg.gym.models.GymLeague;
import com.pokemonurpg.gym.service.BadgeService;
import com.pokemonurpg.gym.service.GymLeagueService;
import com.pokemonurpg.image.models.ImageFolder;
import com.pokemonurpg.image.service.ImageFolderService;
import com.pokemonurpg.item.models.Item;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.site.models.MenuItem;
import com.pokemonurpg.site.service.MenuItemService;
import com.pokemonurpg.species.models.Species;
import com.pokemonurpg.species.models.Type;
import com.pokemonurpg.species.service.SpeciesService;
import com.pokemonurpg.species.service.TypeService;
import com.pokemonurpg.member.models.Member;
import com.pokemonurpg.member.models.Permission;
import com.pokemonurpg.member.models.Role;
import com.pokemonurpg.member.service.PermissionService;
import com.pokemonurpg.member.service.RoleService;
import com.pokemonurpg.creative.models.ParkLocation;
import com.pokemonurpg.creative.models.ParkRank;
import com.pokemonurpg.creative.models.StoryRank;
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
    private ContestAttributeService contestAttributeService;

    @Mock
    private DPPContestMoveTypeService dppContestMoveTypeService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private MemberService memberService;

    @Mock
    private MenuItemService menuItemService;

    @Mock
    private NatureService natureService;

    @Mock
    private ObtainedService obtainedService;

    @Mock
    private ORASContestMoveTypeService orasContestMoveTypeService;

    @Mock
    private ParkLocationService parkLocationService;

    @Mock
    private ParkRankService parkRankService;

    @Mock
    private PermissionService permissionService;

    @Mock
    private RoleService roleService;

    @Mock
    private RSEContestMoveTypeService rseContestMoveTypeService;

    @Mock
    private SectionService sectionService;

    @Mock
    private SpeciesService speciesService;

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
    public void returnsContestAttributeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ContestAttribute.class);
        assertEquals(contestAttributeService, service);
    }

    @Test
    public void returnsDppContestMoveTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(DPPContestMoveType.class);
        assertEquals(dppContestMoveTypeService, service);
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
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Obtained.class);
        assertEquals(obtainedService, service);
    }

    @Test
    public void returnsOrasContestMoveTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(ORASContestMoveType.class);
        assertEquals(orasContestMoveTypeService, service);
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
    public void returnsRseContestMoveTypeService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(RSEContestMoveType.class);
        assertEquals(rseContestMoveTypeService, service);
    }

    @Test
    public void returnsSectionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Section.class);
        assertEquals(sectionService, service);
    }

    @Test
    public void returnsSpeciesService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Species.class);
        assertEquals(speciesService, service);
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