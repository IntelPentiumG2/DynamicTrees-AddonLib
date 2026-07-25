package com.dtteam.dtaddon_lib.fruits;

import com.dtteam.dynamictrees.api.registry.TypedRegistry;
import com.dtteam.dynamictrees.block.fruit.Fruit;
import com.dtteam.dynamictrees.block.fruit.FruitBlock;
import com.dtteam.dtaddon_lib.blocks.fruit.FallingFruitBlock;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockBehaviour;

// From DTPHC2
public class FallingFruit extends Fruit {

    public static final TypedRegistry.EntryType<Fruit> TYPE = TypedRegistry.newType(FallingFruit::new);

    public FallingFruit(Identifier registryName) {
        super(registryName);
    }

    @Override
    protected FruitBlock makeBlock(Identifier id, BlockBehaviour.Properties properties) {
        return new FallingFruitBlock(id, properties, this);
    }
}
