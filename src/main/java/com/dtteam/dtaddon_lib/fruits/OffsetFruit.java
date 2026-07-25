package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

// From DTPHC2
public class OffsetFruit extends Fruit {

    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(OffsetFruit::new);

    public OffsetFruit(Identifier registryName) {
        super(registryName);
    }

    @Override
    protected FruitBlock makeBlock(Identifier id, Block.Properties properties) {
        return new FruitBlock(id, properties, this){
            @Override
            public boolean isSupported(LevelReader world, BlockPos pos, BlockState state) {
                return world.getBlockState(pos.above()).getBlock() instanceof LeavesBlock || world.getBlockState(pos.above(2)).getBlock() instanceof LeavesBlock;
            }
        };
    }

}
