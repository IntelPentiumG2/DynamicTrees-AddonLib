package com.dtteam.dtaddon_lib;

import com.dtteam.dtaddon_lib.data.DTAddonLibExtraModelGenerator;
import com.dtteam.dtaddon_lib.init.DTAddonLibBlocks;
//import com.dtteam.dtaddon_lib.init.DTAddonLibPlusRegistries;
import com.dtteam.dtaddon_lib.init.DTAddonLibRegistries;
import com.dtteam.dynamictrees.treepack.Resources;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.block.soil.SoilProperties;
import com.dtteam.dynamictrees.data.GatherDataHelper;
import com.dtteam.dynamictrees.registry.NeoForgeRegistryHandler;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
//import com.dtteam.dynamictreesplus.block.mushroom.CapProperties;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DynamicTreesAddonLib.MOD_ID)
public final class DynamicTreesAddonLib {

    public static final String MOD_ID = "dtaddon_lib";

    public DynamicTreesAddonLib(IEventBus eventBus, ModContainer container) {
        eventBus.addListener(this::commonSetup);
        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::gatherServerData);
        eventBus.addListener(this::gatherClientData);

        DTAddonLibBlocks.register(eventBus);

        DTAddonLibRegistries.SOUNDS.register(eventBus);

//        if (ModList.get().isLoaded("dynamictreesplus")) {
//            eventBus.register(DTAddonLibPlusRegistries.class);
//        }

        NeoForgeRegistryHandler.setup(MOD_ID, eventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DTAddonLibRegistries.setup();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    private void gatherServerData(final GatherDataEvent.Server event) {
        GatherDataHelper.gatherServerData(MOD_ID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY
                //,CapProperties.REGISTRY
        );
    }

    private void gatherClientData(final GatherDataEvent.Client event) {
        // GatherDataEvent only fires for mods named in --mod, so Dynamic Trees' own listener
        // does not run here. Flag the loaded tree pack entries for generation ourselves.
        Resources.MANAGER.gatherData();
        GatherDataHelper.addModelGenerator(MOD_ID, new DTAddonLibExtraModelGenerator());
        GatherDataHelper.gatherClientData(MOD_ID, event,
                SoilProperties.REGISTRY,
                Family.REGISTRY,
                Species.REGISTRY,
                LeavesProperties.REGISTRY,
                Fruit.REGISTRY,
                Pod.REGISTRY
                //,CapProperties.REGISTRY
        );
    }

    public static Identifier location(final String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

}
