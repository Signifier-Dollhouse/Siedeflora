package com.signifier.siedeflora.block;

import com.signifier.siedeflora.block.entity.SoilBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.signifier.siedeflora.Siedeflora.MODID;

public class BlockRegistry
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, MODID);
    public static final DeferredHolder<Block, SoilBlock> SOIL = BLOCKS.register("soil", () -> new SoilBlock(
            Block.Properties.of().strength(1.0f))
    );
    public static final DeferredHolder<Block, AdvancedCropBlock> ADVANCED_CROP = BLOCKS.register("test_crop",
                                                                                                 () -> new AdvancedCropBlock(
                                                                                                         Block.Properties.of().strength(
                                                                                                                 0

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SoilBlockEntity>> SOIL_BETYPE = BLOCK_ENTITIES.register(
            "soil", () -> BlockEntityType.Builder.of(SoilBlockEntity::new, SOIL.get()).build(null));

}
