package com.signifier.siedeflora.item;

import com.signifier.siedeflora.Siedeflora;
import com.signifier.siedeflora.block.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistry
{
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Siedeflora.MODID);
    public static final DeferredItem<BlockItem> SOIL = ITEMS.registerSimpleBlockItem(BlockRegistry.SOIL);
    public static final DeferredItem<BlockItem> ADVANCED_CROP = ITEMS.registerSimpleBlockItem(BlockRegistry.ADVANCED_CROP);
}
