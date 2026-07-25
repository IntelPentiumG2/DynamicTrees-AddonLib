package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

// From DTPHC2
public class CobwebFruit extends Fruit {

    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(CobwebFruit::new);

    public CobwebFruit(Identifier registryName) {
        super(registryName);
    }

    @Override
    protected FruitBlock makeBlock(Identifier id, Block.Properties properties) {
        return new FruitBlock(id, properties.noCollision(), this){
            @Override
            protected void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity, InsideBlockEffectApplier pEffectApplier, boolean pIsPrecise) {
                pEntity.makeStuckInBlock(pState, new Vec3(0.25D, (double)0.05F, 0.25D));
            }
        };
    }

    @Override
    public MapColor getDefaultMapColor() {
        return MapColor.WOOL;
    }

}
