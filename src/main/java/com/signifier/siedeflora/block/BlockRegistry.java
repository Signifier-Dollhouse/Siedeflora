package com.signifier.siedeflora.block;

import com.signifier.siedeflora.block.entity.SoilBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.signifier.siedeflora.Siedeflora.MODID;

public interface BlockRegistry {
    DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    RegistryObject<SoilBlock> SOIL = BLOCKS.register("soil", () -> new SoilBlock(
            Block.Properties.of().strength(1.0f))
    );
    RegistryObject<AdvancedCropBlock> ADVANCED_CROP = BLOCKS.register("test_crop",
            () -> new AdvancedCropBlock(
                    Block.Properties.of().strength(0.5f))
    );

    DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(
            ForgeRegistries.BLOCK_ENTITY_TYPES, MODID);
    RegistryObject<BlockEntityType<SoilBlockEntity>> SOIL_BETYPE = BLOCK_ENTITIES.register(
            "soil", () -> BlockEntityType.Builder.of(SoilBlockEntity::new, SOIL.get()).build(null));

}
