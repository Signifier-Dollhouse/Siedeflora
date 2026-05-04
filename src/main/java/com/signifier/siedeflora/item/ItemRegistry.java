package com.signifier.siedeflora.item;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface ItemRegistry
{
    DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Siedeflora.MODID);
    RegistryObject<BlockItem> SOIL = registerSimpleBlockItem(BlockRegistry.SOIL);
    RegistryObject<BlockItem> ADVANCED_CROP = registerSimpleBlockItem(BlockRegistry.ADVANCED_CROP);

    static RegistryObject<BlockItem> registerSimpleBlockItem(RegistryObject<? extends Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
