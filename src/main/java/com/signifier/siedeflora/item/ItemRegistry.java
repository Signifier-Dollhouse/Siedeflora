package com.signifier.siedeflora.item;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ItemRegistry
{
    DeferredRegister.Items ITEMS = DeferredRegister.createItems(Siedeflora.MODID);

    DeferredItem<Item> DEBUGGER = ITEMS.registerItem("debugger", id -> new CreativeGlovesItem(new Item.Properties()));

    DeferredItem<BlockItem> SOIL = registerSimpleBlockItem(BlockRegistry.SOIL);
//    DeferredItem<BlockItem> ADVANCED_CROP = registerSimpleBlockItem(BlockRegistry.ADVANCED_CROP);

    static DeferredItem<BlockItem> registerSimpleBlockItem(DeferredBlock<?> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
