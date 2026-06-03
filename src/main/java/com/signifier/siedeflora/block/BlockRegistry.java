package com.signifier.siedeflora.block;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.block.entity.SoilBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface BlockRegistry {
    DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Siedeflora.MODID);
    DeferredBlock<SoilBlock> SOIL = BLOCKS.register("soil", () -> new SoilBlock(
            Block.Properties.of().strength(1.0f))
    );
//    DeferredBlock<AdvancedCropBlock> ADVANCED_CROP = BLOCKS.register("test_crop",
//            () -> new AdvancedCropBlock(
//                    Block.Properties.of().strength(0.5f))
//    );

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            Registries.BLOCK_ENTITY_TYPE, Siedeflora.MODID);
    DeferredHolder<BlockEntityType<?>, BlockEntityType<SoilBlockEntity>> SOIL_BETYPE = BLOCK_ENTITIES.register(
            "soil", () -> new BlockEntityType<>(SoilBlockEntity::new, SOIL.get()));

}
