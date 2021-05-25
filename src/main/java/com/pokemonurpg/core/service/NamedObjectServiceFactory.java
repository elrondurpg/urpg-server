package com.pokemonurpg.core.service;

import com.pokemonurpg.ability.service.AbilityService;
import com.pokemonurpg.attack.service.AttackCategoryService;
import com.pokemonurpg.attack.service.AttackService;
import com.pokemonurpg.attack.service.AttackTargetTypeService;
import com.pokemonurpg.contest.service.ContestAttributeService;
import com.pokemonurpg.contest.service.DPPContestMoveTypeService;
import com.pokemonurpg.contest.service.ORASContestMoveTypeService;
import com.pokemonurpg.contest.service.RSEContestMoveTypeService;
import com.pokemonurpg.creative.service.ArtRankService;
import com.pokemonurpg.creative.service.ParkLocationService;
import com.pokemonurpg.creative.service.ParkRankService;
import com.pokemonurpg.creative.service.StoryRankService;
import com.pokemonurpg.general.service.NatureService;
import com.pokemonurpg.general.service.ObtainedService;
import com.pokemonurpg.general.service.SectionService;
import com.pokemonurpg.gym.service.*;
import com.pokemonurpg.image.service.ImageFolderService;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.site.service.MenuItemService;
import com.pokemonurpg.species.service.SpeciesService;
import com.pokemonurpg.species.service.TypeService;
import com.pokemonurpg.member.service.PermissionService;
import com.pokemonurpg.member.service.RoleService;
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
    private ContestAttributeService contestAttributeService;

    @Resource
    private DPPContestMoveTypeService dppContestMoveTypeService;

    @Resource
    private GymService gymService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private ItemService itemService;

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
                case "ContestAttribute": return contestAttributeService;
                case "DPPContestMoveType": return dppContestMoveTypeService;
                case "Gym": return gymService;
                case "GymLeague": return gymLeagueService;
                case "ImageFolder": return imageFolderService;
                case "Item": return itemService;
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
