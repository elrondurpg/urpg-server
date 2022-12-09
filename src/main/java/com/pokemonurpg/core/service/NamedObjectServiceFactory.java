package com.pokemonurpg.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pokemonurpg.configuration.v1.creative.parklocation.service.ParkLocationService;
import com.pokemonurpg.configuration.v1.creative.parkrank.service.ParkRankService;
import com.pokemonurpg.configuration.v1.creative.storyrank.service.StoryRankService;
import com.pokemonurpg.configuration.v1.item.service.ItemBundleService;
import com.pokemonurpg.configuration.v1.item.service.ItemService;
import com.pokemonurpg.configuration.v1.member.member.service.MemberService;
import com.pokemonurpg.configuration.v1.member.permission.PermissionService;
import com.pokemonurpg.configuration.v1.member.role.RoleService;
import com.pokemonurpg.configuration.v1.pokemon.ability.service.AbilityService;
import com.pokemonurpg.configuration.v1.pokemon.capturemethod.service.CaptureMethodService;
import com.pokemonurpg.configuration.v1.pokemon.nature.service.NatureService;
import com.pokemonurpg.configuration.v1.pokemon.species.service.SpeciesService;
import com.pokemonurpg.configuration.v1.pokemon.type.service.TypeService;
import com.pokemonurpg.configuration.v1.site.flag.service.FlagService;
import com.pokemonurpg.configuration.v1.site.image.service.ImageFolderService;
import com.pokemonurpg.configuration.v1.site.menu.MenuItemService;
import com.pokemonurpg.configuration.v1.site.section.service.SectionService;

@Service
public class NamedObjectServiceFactory {

    @Resource
    private AbilityService abilityService;

    @Resource
    private FlagService flagService;

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
            }
        }
        return null;
    }

}
