package com.dtteam.dtaddon_lib.init;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.blocks.leaves.*;
import com.dtteam.dtaddon_lib.cell.DTAddonLibCellKits;
import com.dtteam.dtaddon_lib.fruits.PalmPod;
import com.dtteam.dtaddon_lib.genfeature.DTAddonLibGenFeatures;
import com.dtteam.dtaddon_lib.growthlogic.DTAddonLibGrowthLogicKits;
import com.dtteam.dtaddon_lib.tree.family.*;
import com.dtteam.dtaddon_lib.tree.species.*;
import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.event.RegistryEvent;
import com.dtteam.dynamictrees.event.TypeRegistryEvent;
import com.dtteam.dynamictrees.block.leaves.LeavesProperties;
import com.dtteam.dynamictrees.systems.growthlogic.GrowthLogicKit;
import com.dtteam.dynamictrees.block.CommonVoxelShapes;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.systems.genfeature.GenFeature;
import com.dtteam.dynamictrees.block.pod.Pod;
import com.dtteam.dynamictrees.tree.family.Family;
import com.dtteam.dynamictrees.tree.species.Species;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber
public class DTAddonLibRegistries {

    public static final TagKey<Block> CAN_BE_SPILED = BlockTags.create(DynamicTreesAddonLib.location("can_be_spiled"));

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, DynamicTreesAddonLib.MOD_ID);
    public static final Supplier<SoundEvent> FRUIT_BONK = registerSound("falling_fruit.bonk");

    public static final VoxelShape DRAGON_FRUIT_CACTUS_SAPLING_SHAPE = Shapes.box(
                    0.375f, 0.0f, 0.375f,
                    0.625f, 0.5f, 0.625f);

    public static final VoxelShape BANANA_SAPLING_SHAPE = Shapes.box(
                    0.375f, 0.0f, 0.375f,
                    0.625f, 0.9375f, 0.625f);

    public static void setup() {
        ShapeRegitries();
        DTAddonLibCapShapeRegistries.register();
    }

    public static Supplier<SoundEvent> registerSound (String name){
        return SOUNDS.register(name, ()-> SoundEvent.createVariableRangeEvent(DynamicTreesAddonLib.location(name)));
    }

    public static void ShapeRegitries() {
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("dragon_fruit_cactus").toString(), DRAGON_FRUIT_CACTUS_SAPLING_SHAPE);
        CommonVoxelShapes.SHAPES.put(DynamicTreesAddonLib.location("banana_sapling").toString(), BANANA_SAPLING_SHAPE);
    }

    @SubscribeEvent
    public static void onGenFeatureRegistry (final RegistryEvent<GenFeature> event) {
        if (event.isEntryOfType(GenFeature.class)) {
            DTAddonLibGenFeatures.register(event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void onCellKitRegistry (final RegistryEvent<CellKit> event) {
        if (event.isEntryOfType(CellKit.class)) {
            DTAddonLibCellKits.register(event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void onGrowthLogicKitRegistry (final RegistryEvent<GrowthLogicKit> event) {
        if (event.isEntryOfType(GrowthLogicKit.class)) {
            DTAddonLibGrowthLogicKits.register(event.getRegistry());
        }
    }

    @SubscribeEvent
    public static void registerLeavesPropertiesTypes(TypeRegistryEvent<LeavesProperties> event) {
        if (event.isEntryOfType(LeavesProperties.class)) {
            event.registerType(DynamicTreesAddonLib.location("cobweb"), CobwebLeavesProperties.TYPE);
            event.registerType(DynamicTreesAddonLib.location("particle"), ParticleLeavesProperties.TYPE);
            event.registerType(DynamicTreesAddonLib.location("scruffy_particle"), ScruffyParticleLeavesProperties.TYPE);
            event.registerType(DynamicTreesAddonLib.location("dragon_fruit"), DragonFruitLeavesProperties.TYPE);
        }
    }

    @SubscribeEvent
    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
        if (event.isEntryOfType(Species.class)) {
            event.registerType(DynamicTreesAddonLib.location("poplar"), PoplarSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("twiglet"), TwigletSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("generates_underwater"), GenUnderwaterSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("generates_on_extra_soil"), GenOnExtraSoilSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("place_alternate"), PlaceAlternateLavaSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("grow_on_lava"), GrowOnLavaSpecies.TYPE);
            event.registerType(DynamicTreesAddonLib.location("fruit_log"), FruitLogSpecies.TYPE);
        }
    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        if (event.isEntryOfType(Family.class)) {
            event.registerType(DynamicTreesAddonLib.location("diagonal_palm"), DiagonalPalmFamily.TYPE);
            event.registerType(DynamicTreesAddonLib.location("stripped_transition_log"), TransitionLogFamily.TYPE_STRIPPED);
            event.registerType(DynamicTreesAddonLib.location("base_transition_log"), TransitionLogFamily.TYPE_BASE);
        }
    }

//    @SubscribeEvent
//    public static void registerFruitType(final TypeRegistryEvent<Fruit> event) {
//        if (event.isEntryOfType(Fruit.class)) {
//            event.registerType(DynamicTreesAddonLib.location("offset_down"), OffsetFruit.TYPE);
//            event.registerType(DynamicTreesAddonLib.location("falling_fruit"), FallingFruit.TYPE);
//            event.registerType(DynamicTreesAddonLib.location("cobweb"), CobwebFruit.TYPE);
//        }
//    }
//
    @SubscribeEvent
    public static void registerPodType(final TypeRegistryEvent<Pod> event) {
        if (event.isEntryOfType(Pod.class)) {
            event.registerType(DynamicTreesAddonLib.location("palm"), PalmPod.TYPE);
            // falling_palm needs a custom PodBlock, which Dynamic Trees 1.8.0 no longer lets an
            // add-on supply (Pod.createBlock is final and hardcodes PodBlock). Restore this once
            // the block factory hook is back.
            // event.registerType(DynamicTreesAddonLib.location("falling_palm"), FallingPalmPod.TYPE);
        }
    }
}
