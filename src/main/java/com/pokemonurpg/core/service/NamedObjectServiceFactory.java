package com.pokemonurpg.core.service;

import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.creative.parklocation.service.ParkLocationService;
import com.pokemonurpg.configuration.v1.creative.parkrank.service.ParkRankService;
import com.pokemonurpg.configuration.v1.creative.storyrank.service.StoryRankService;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;
import com.pokemonurpg.configuration.v1.site.section.service.SectionService;
import com.pokemonurpg.gym.service.*;
import com.pokemonurpg.image.service.ImageFolderService;
import com.pokemonurpg.item.service.ItemBundleService;
import com.pokemonurpg.item.service.ItemService;
import com.pokemonurpg.member.service.MemberService;
import com.pokemonurpg.site.service.MenuItemService;
import com.pokemonurpg.member.service.PermissionService;
import com.pokemonurpg.member.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NamedObjectServiceFactory {

    @Resource
    private AbilityService abilityService;

    @Resource
    private ChampionService championService;

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
    private MemberService memberService;

    @Resource
    private MenuItemService menuItemService;

    @Resource
    private NatureService natureService;

    @Resource
    private CaptureMethodService obtainedService;

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
                case "Champion": return championService;
                case "EliteFour": return eliteFourService;
                case "Gym": return gymService;
                case "GymLeague": return gymLeagueService;
                case "ImageFolder": return imageFolderService;
                case "Item": return itemService;
                case "ItemBundle": return itemBundleService;
                case "Member": return memberService;
                case "MenuItem": return menuItemService;
                case "Permission": return permissionService;
                case "Role": return roleService;
            }
        }
        return null;
    }

}
