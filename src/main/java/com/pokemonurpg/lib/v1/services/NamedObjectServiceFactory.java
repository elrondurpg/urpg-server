package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.configuration.v1.abilities.AbilityService;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeService;
import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.capturemethods.ObtainedService;
import com.pokemonurpg.configuration.v1.champions.KnownChampionService;
import com.pokemonurpg.configuration.v1.championslots.ChampionService;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeService;
import com.pokemonurpg.configuration.v1.contestgenerations.ContestTypeService;
import com.pokemonurpg.configuration.v1.contestmovetypes.ORASContestMoveTypeService;
import com.pokemonurpg.configuration.v1.contestmovetypes.RSEContestMoveTypeService;
import com.pokemonurpg.configuration.v1.contestranks.ContestRankService;
import com.pokemonurpg.configuration.v1.elitefourmembers.KnownEliteFourMemberService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourService;
import com.pokemonurpg.configuration.v1.featureflags.FlagService;
import com.pokemonurpg.configuration.v1.gymleaders.KnownGymLeaderService;
import com.pokemonurpg.configuration.v1.gymleagues.GymLeagueService;
import com.pokemonurpg.configuration.v1.gyms.GymService;
import com.pokemonurpg.configuration.v1.imagefolders.ImageFolderService;
import com.pokemonurpg.configuration.v1.itembundles.ItemBundleService;
import com.pokemonurpg.configuration.v1.items.ItemService;
import com.pokemonurpg.configuration.v1.members.MemberService;
import com.pokemonurpg.configuration.v1.natures.NatureService;
import com.pokemonurpg.configuration.v1.parklocations.ParkLocationService;
import com.pokemonurpg.configuration.v1.parkranks.ParkRankService;
import com.pokemonurpg.configuration.v1.permissions.PermissionService;
import com.pokemonurpg.configuration.v1.pokemon.SpeciesService;
import com.pokemonurpg.configuration.v1.roles.RoleService;
import com.pokemonurpg.configuration.v1.sections.SectionService;
import com.pokemonurpg.configuration.v1.sitemenuitems.MenuItemService;
import com.pokemonurpg.configuration.v1.storyranks.StoryRankService;
import com.pokemonurpg.configuration.v1.types.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NamedObjectServiceFactory {

    @Resource
    private AbilityService abilityService;

    @Resource
    private ArtRankService artRankService;

    @Resource
    private AttackCategoryService attackCategoryService;

    @Resource
    private AttackService attackService;

    @Resource
    private AttackTargetTypeService attackTargetTypeService;

    @Resource
    private BadgeService badgeService;

    @Resource
    private ChampionService championService;

    @Resource
    private ContestAttributeService contestAttributeService;

    @Resource
    private ContestRankService contestRankService;

    @Resource
    private ContestTypeService contestTypeService;

    @Resource
    private EliteFourService eliteFourService;

    @Resource
    private FlagService flagService;

    @Resource
    private GymService gymService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private ItemService itemService;

    @Resource
    private ItemBundleService itemBundleService;

    @Resource
    private KnownChampionService knownChampionService;

    @Resource
    private KnownEliteFourMemberService knownEliteFourMemberService;

    @Resource
    private KnownGymLeaderService knownGymLeaderService;

    @Resource
    private MemberService memberService;

    @Resource
    private MenuItemService menuItemService;

    @Resource
    private NatureService natureService;

    @Resource
    private ObtainedService obtainedService;

    @Resource
    private ORASContestMoveTypeService orasContestMoveTypeService;

    @Resource
    private ParkLocationService parkLocationService;

    @Resource
    private ParkRankService parkRankService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Resource
    private RSEContestMoveTypeService rseContestMoveTypeService;

    @Resource
    private SectionService sectionService;

    @Resource
    private SpeciesService speciesService;

    @Resource
    private StoryRankService storyRankService;

    @Resource
    private TypeService typeService;

    @Resource
    private ImageFolderService imageFolderService;

    public NamedObjectService getServiceForClass(Class type) {
        if (type != null) {
            switch (type.getSimpleName()) {
                case "Ability": return abilityService;
                case "ArtRank": return artRankService;
                case "AttackCategory": return attackCategoryService;
                case "Attack": return attackService;
                case "AttackTargetType": return attackTargetTypeService;
                case "Badge": return badgeService;
                case "Champion": return championService;
                case "ContestAttribute": return contestAttributeService;
                case "ContestRank": return contestRankService;
                case "ContestType": return contestTypeService;
                case "EliteFour": return eliteFourService;
                case "Flag": return flagService;
                case "Gym": return gymService;
                case "GymLeague": return gymLeagueService;
                case "ImageFolder": return imageFolderService;
                case "Item": return itemService;
                case "ItemBundle": return itemBundleService;
                case "KnownChampion": return knownChampionService;
                case "KnownEliteFourMember": return knownEliteFourMemberService;
                case "KnownGymLeader": return knownGymLeaderService;
                case "Member": return memberService;
                case "MenuItem": return menuItemService;
                case "Nature": return natureService;
                case "Obtained": return obtainedService;
                case "ORASContestMoveType": return orasContestMoveTypeService;
                case "ParkLocation": return parkLocationService;
                case "ParkRank": return parkRankService;
                case "Permission": return permissionService;
                case "Role": return roleService;
                case "RSEContestMoveType": return rseContestMoveTypeService;
                case "Section": return sectionService;
                case "Species": return speciesService;
                case "StoryRank": return storyRankService;
                case "Type": return typeService;
            }
        }
        return null;
    }

}
