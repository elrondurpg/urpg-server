package com.pokemonurpg.lib.v1.services;

import com.pokemonurpg.configuration.v1.abilities.AbilityService;
import com.pokemonurpg.configuration.v1.artranks.ArtRankService;
import com.pokemonurpg.configuration.v1.attackcategories.AttackCategoryService;
import com.pokemonurpg.configuration.v1.attacks.AttackService;
import com.pokemonurpg.configuration.v1.attacktargettypes.AttackTargetTypeService;
import com.pokemonurpg.configuration.v1.badges.BadgeService;
import com.pokemonurpg.configuration.v1.capturemethods.CaptureMethodService;
import com.pokemonurpg.configuration.v1.champions.ChampionService;
import com.pokemonurpg.configuration.v1.championslots.ChampionSlotService;
import com.pokemonurpg.configuration.v1.contestattributes.ContestAttributeService;
import com.pokemonurpg.configuration.v1.contestgenerations.ContestGenerationService;
import com.pokemonurpg.configuration.v1.contesteffects.ContestEffectService;
import com.pokemonurpg.configuration.v1.contestranks.ContestRankService;
import com.pokemonurpg.configuration.v1.elitefourmembers.EliteFourMemberService;
import com.pokemonurpg.configuration.v1.elitefourmemberslots.EliteFourMemberSlotService;
import com.pokemonurpg.configuration.v1.featureflags.FeatureFlagService;
import com.pokemonurpg.configuration.v1.gymleaders.GymLeaderService;
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
import com.pokemonurpg.configuration.v1.pokemon.PokemonService;
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
    private ChampionSlotService championSlotService;

    @Resource
    private ContestAttributeService contestAttributeService;

    @Resource
    private ContestRankService contestRankService;

    @Resource
    private ContestGenerationService contestGenerationService;

    @Resource
    private EliteFourMemberSlotService eliteFourMemberSlotService;

    @Resource
    private FeatureFlagService featureFlagService;

    @Resource
    private GymService gymService;

    @Resource
    private GymLeagueService gymLeagueService;

    @Resource
    private ItemService itemService;

    @Resource
    private ItemBundleService itemBundleService;

    @Resource
    private ChampionService championService;

    @Resource
    private EliteFourMemberService eliteFourMemberService;

    @Resource
    private GymLeaderService gymLeaderService;

    @Resource
    private MemberService memberService;

    @Resource
    private MenuItemService menuItemService;

    @Resource
    private NatureService natureService;

    @Resource
    private CaptureMethodService captureMethodService;

    @Resource
    private ContestEffectService contestEffectService;

    @Resource
    private ParkLocationService parkLocationService;

    @Resource
    private ParkRankService parkRankService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RoleService roleService;

    @Resource
    private SectionService sectionService;

    @Resource
    private PokemonService pokemonService;

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
                case "ChampionSlot": return championSlotService;
                case "ContestAttribute": return contestAttributeService;
                case "ContestRank": return contestRankService;
                case "ContestGeneration": return contestGenerationService;
                case "EliteFourMemberSlot": return eliteFourMemberSlotService;
                case "FeatureFlag": return featureFlagService;
                case "Gym": return gymService;
                case "GymLeague": return gymLeagueService;
                case "ImageFolder": return imageFolderService;
                case "Item": return itemService;
                case "ItemBundle": return itemBundleService;
                case "Champion": return championSlotService;
                case "EliteFourMember": return eliteFourMemberService;
                case "GymLeader": return gymLeaderService;
                case "Member": return memberService;
                case "MenuItem": return menuItemService;
                case "Nature": return natureService;
                case "CaptureMethod": return captureMethodService;
                case "ContestEffect": return contestEffectService;
                case "ParkLocation": return parkLocationService;
                case "ParkRank": return parkRankService;
                case "Permission": return permissionService;
                case "Role": return roleService;
                case "Section": return sectionService;
                case "Pokemon": return pokemonService;
                case "StoryRank": return storyRankService;
                case "Type": return typeService;
            }
        }
        return null;
    }

}
