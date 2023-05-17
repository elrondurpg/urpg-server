package com.pokemonurpg.lib.service;

import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.entities.*;
import com.pokemonurpg.configuration.v1.abilities.AbilityService;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeService;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeService;
import com.pokemonurpg.configuration.v1.contestmovetypes.DPPContestMoveTypeService;
import com.pokemonurpg.configuration.v1.contestmovetypes.ORASContestMoveTypeService;
import com.pokemonurpg.configuration.v1.contestmovetypes.RSEContestMoveTypeService;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import com.pokemonurpg.configuration.v1.natures.NatureService;
import com.pokemonurpg.configuration.v1.capturemethods.ObtainedService;
import com.pokemonurpg.configuration.v1.sections.SectionService;
import com.pokemonurpg.entities.ImageFolder;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import com.pokemonurpg.entities.Item;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.entities.MenuItem;
import com.pokemonurpg.configuration.v1.sitemenuitem.MenuItemService;
import com.pokemonurpg.entities.Species;
import com.pokemonurpg.entities.Type;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import com.pokemonurpg.entities.Member;
import com.pokemonurpg.entities.Permission;
import com.pokemonurpg.entities.Role;
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
    private ChampionService championService;

    @Mock
    private ContestAttributeService contestAttributeService;

    @Mock
    private DPPContestMoveTypeService dppContestMoveTypeService;

    @Mock
    private EliteFourService eliteFourService;

    @Mock
    private GymService gymService;

    @Mock
    private GymLeagueService gymLeagueService;

    @Mock
    private ItemService itemService;

    @Mock
    private KnownChampionService knownChampionService;

    @Mock
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Mock
    private KnownGymLeaderService knownGymLeaderService;

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
    public void returnsChampionService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(Champion.class);
        assertEquals(championService, service);
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
    public void returnsEliteFourService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(EliteFour.class);
        assertEquals(eliteFourService, service);
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
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(KnownChampion.class);
        assertEquals(knownChampionService, service);
    }

    @Test
    public void returnsKnownEliteFourMemberService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(KnownEliteFourMember.class);
        assertEquals(knownEliteFourMemberService, service);
    }

    @Test
    public void returnsKnownGymLeaderService() {
        NamedObjectService service = namedObjectServiceFactory.getServiceForClass(KnownGymLeader.class);
        assertEquals(knownGymLeaderService, service);
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