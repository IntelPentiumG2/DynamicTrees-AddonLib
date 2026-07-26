package com.dtteam.dtaddon_lib.data;

import com.dtteam.dtaddon_lib.DynamicTreesAddonLib;
import com.dtteam.dtaddon_lib.init.DTAddonLibBlocks;
import com.dtteam.dynamictrees.data.BiGenerator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.world.level.block.Block;

/**
 * Block state definitions for the blocks this mod registers itself rather than through a Dynamic
 * Trees registry, which the per-entry generators therefore never see.
 *
 * <p>Both spiles keep their hand-authored definitions: the multipart dispatch over facing, fill
 * level and syrup stage is beyond what these generators can express. What is emitted here is only
 * a placeholder that satisfies vanilla's "every block has a block state definition" check, and
 * {@code duplicatesStrategy = EXCLUDE} keeps the authored copy ahead of it on the resource path.
 * </p>
 */
public class DTAddonLibExtraModelGenerator implements BiGenerator<BlockModelGenerators, ItemModelGenerators, String> {

    @Override
    public void generate(BlockModelGenerators blockModels, ItemModelGenerators itemModels, String input, Dependencies deps) {
        placeholder(blockModels, DTAddonLibBlocks.MAPLE_SPILE_BLOCK.get(), "maple_spile_empty");
        placeholder(blockModels, DTAddonLibBlocks.MAPLE_SPILE_BUCKET_BLOCK.get(), "maple_spile_bucket");
    }

    private static void placeholder(BlockModelGenerators blockModels, Block block, String model) {
        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block,
                        BlockModelGenerators.plainVariant(DynamicTreesAddonLib.location("block/" + model)))
        );
    }

    @Override
    public Dependencies gatherDependencies(String input) {
        return new Dependencies();
    }

}
